package com.github.raininforest.gerberpcb.model.entity

import com.github.raininforest.GraphicsObject
import java.util.*

/**
 * Created by Sergey Velesko on 31.10.2021
 */
data class Gerber(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    var isVisible: Boolean = false,
    val data: List<GraphicsObject>
)