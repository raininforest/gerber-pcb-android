package com.github.raininforest.gerberpcb.model

data class GerberItem(
    val name: String,
    var color: String = "#ffffff",
    var visible: Boolean = false
)