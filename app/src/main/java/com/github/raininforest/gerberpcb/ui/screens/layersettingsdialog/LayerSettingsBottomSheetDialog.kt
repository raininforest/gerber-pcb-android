package com.github.raininforest.gerberpcb.ui.screens.layersettingsdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.annotation.ColorInt
import androidx.core.os.bundleOf
import com.github.raininforest.gerberpcb.databinding.ItemSettingsDialogBinding
import com.github.raininforest.gerberpcb.model.GerberRepository
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.android.ext.android.inject

class LayerSettingsBottomSheetDialog : BottomSheetDialogFragment() {

    private var _binding: ItemSettingsDialogBinding? = null
    private val binding get() = _binding!!

    private val gerberRepository: GerberRepository by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ItemSettingsDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gerberId = arguments?.getString(GERBER_ID_KEY)
        gerberId?.let {
            @ColorInt val color = gerberRepository.getColor(gerberId)
            val opacity = gerberRepository.getOpacity(gerberId)
            binding.opacitySeekbar.progress = (opacity * PERCENT_100).toInt()
            binding.opacitySeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    // ignore
                }
                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    // ignore
                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    gerberRepository.setOpacity(gerberId, seekBar.progress / PERCENT_100)
                }
            })
            binding.colorPanel.setInitialColor(color)
            binding.colorPanel.onColorSelected = { clr: Int ->
                gerberRepository.setColor(gerberId, clr)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun create(gerberId: String): LayerSettingsBottomSheetDialog {
            val bundle = bundleOf(GERBER_ID_KEY to gerberId)
            return LayerSettingsBottomSheetDialog().apply { arguments = bundle }
        }

        const val GERBER_ID_KEY = "gerber_id_key"
        const val TAG = "ModalBottomSheet"
        private const val PERCENT_100 = 100f
    }

}
