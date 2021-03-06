package com.github.raininforest.gerberpcb.ui.screens.graphicsscreen.gerberview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Matrix
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.MotionEvent.INVALID_POINTER_ID
import android.view.ScaleGestureDetector
import android.view.View
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GerberView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle), KoinComponent {

    private var scaleGestureDetector: ScaleGestureDetector? = null
    private var activePointerId: Int? = null

    private var lastTouchX = 0.0f
    private var lastTouchY = 0.0f

    private val drawMatrix: Matrix = Matrix()

    private val renderer: GerberRenderer by inject()

    private val _data: MutableList<GerberLayerUi> = mutableListOf()
    var data: List<GerberLayerUi>
        set(value) {
            _data.clear()
            _data.addAll(value)
        }
        get() = _data

    init {
        scaleGestureDetector = ScaleGestureDetector(context, object :
            ScaleGestureDetector.OnScaleGestureListener {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                var scaleFactor = detector.scaleFactor
                scaleFactor = MIN_SCALE_FACTOR.coerceAtLeast(
                    scaleFactor.coerceAtMost(MAX_SCALE_FACTOR)
                )
                val focusX = detector.focusX
                val focusY = detector.focusY
                drawMatrix.postScale(scaleFactor, scaleFactor, focusX, focusY)
                invalidate()
                return true
            }

            override fun onScaleBegin(detector: ScaleGestureDetector) = true

            override fun onScaleEnd(detector: ScaleGestureDetector) {
                // ignore
            }
        })
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        initDrawMatrix()
    }

    private fun initDrawMatrix() {
        if (_data.isNotEmpty()) {
            val minX = _data.minOf { it.gerberImageSize.minX }
            val minY = _data.minOf { it.gerberImageSize.minY }
            val maxX = _data.maxOf { it.gerberImageSize.maxX }
            val maxY = _data.maxOf { it.gerberImageSize.maxY }

            val xSize = maxX - minX
            val ySize = maxY - minY

            val xScaleFactor = width / xSize
            val yScaleFactor = height / ySize
            val scaleFactor: Float
            val dx: Float
            val dy: Float
            if (xScaleFactor < yScaleFactor) {
                scaleFactor = xScaleFactor
                dx = -minX * scaleFactor
                dy = height.toFloat() / 2 - (ySize / 2 + minY) * scaleFactor
            } else {
                scaleFactor = yScaleFactor
                dx = width.toFloat() / 2 - (xSize / 2 + minX) * scaleFactor
                dy = minY * scaleFactor
            }

            drawMatrix.setScale(1f, -1f, width / 2f, height / 2f)
            drawMatrix.postScale(scaleFactor, scaleFactor, 0f, height.toFloat())
            drawMatrix.postTranslate(dx, -dy)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            with(canvas) {
                save()
                setMatrix(drawMatrix)
                drawData(canvas = this)
                restore()
            }
        }
    }

    private fun drawData(canvas: Canvas) {
        _data.forEach { gerberLayer ->
            renderer.render(
                data = gerberLayer.data,
                canvas = canvas,
                color = gerberLayer.color,
                opacity = gerberLayer.opacity
            )
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        scaleGestureDetector?.onTouchEvent(event)
        val action = event?.action
        action?.let {
            when (action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    lastTouchX = event.x
                    lastTouchY = event.y
                    activePointerId = event.getPointerId(0)
                }
                MotionEvent.ACTION_MOVE -> {
                    activePointerId?.let {
                        val pointerIndex = event.findPointerIndex(it)
                        val x = event.getX(pointerIndex)
                        val y = event.getY(pointerIndex)

                        scaleGestureDetector?.let { scaleGestureDetector ->
                            if (!scaleGestureDetector.isInProgress) {
                                val dx = x - lastTouchX
                                val dy = y - lastTouchY
                                drawMatrix.postTranslate(dx, dy)
                                invalidate()
                            }
                        }
                        lastTouchX = x
                        lastTouchY = y
                    }
                }
                MotionEvent.ACTION_UP -> activePointerId = INVALID_POINTER_ID
                MotionEvent.ACTION_CANCEL -> activePointerId = INVALID_POINTER_ID
                MotionEvent.ACTION_POINTER_UP -> {
                    val pointerIndex =
                        (event.action and MotionEvent.ACTION_POINTER_INDEX_MASK) shr
                                MotionEvent.ACTION_POINTER_INDEX_SHIFT
                    val pointerId = event.getPointerId(pointerIndex)
                    if (pointerId == activePointerId) {
                        val newPointerIndex = if (pointerIndex == 0) 1 else 0
                        lastTouchX = event.getX(newPointerIndex)
                        lastTouchY = event.getY(newPointerIndex)
                        activePointerId = event.getPointerId(newPointerIndex)
                    } else {
                        // ignore
                    }
                }
                else -> {}
            }
        }
        return true
    }

    companion object {
        private const val MIN_SCALE_FACTOR = 0.01f
        private const val MAX_SCALE_FACTOR = 100.0f
    }
}
