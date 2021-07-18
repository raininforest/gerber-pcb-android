package com.github.raininforest.gerberpcb.model

import java.util.*

/**
 * Created by Sergey Velesko on 18.07.2021
 */
data class GerberItemUi(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "<unknown name>",
    val checked: Boolean = false
)