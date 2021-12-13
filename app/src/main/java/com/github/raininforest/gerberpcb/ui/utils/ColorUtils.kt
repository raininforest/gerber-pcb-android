package com.github.raininforest.gerberpcb.ui.utils

import kotlin.random.Random

private const val MIN_COLOR = 100
private const val MAX_COLOR = 255

fun generateLayerColor(): String {
    val red = Integer.toHexString(Random(Random.nextInt()).nextInt(MIN_COLOR, MAX_COLOR))
    val green = Integer.toHexString(Random(Random.nextInt()).nextInt(MIN_COLOR, MAX_COLOR))
    val blue = Integer.toHexString(Random(Random.nextInt()).nextInt(MIN_COLOR, MAX_COLOR))

    return "#$red$green$blue"
}