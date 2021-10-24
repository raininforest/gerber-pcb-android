package com.github.raininforest.syntaxparser.impl.utils

import com.github.raininforest.core.PI

/**
 * Math extensions
 *
 * Created by Sergey Velesko on 23.10.2021
 */

/**
 * Converts [Double] degrees to [Double] radians
 */
fun Double.toRadians() = this * PI / 180

/**
 * Converts [Double] radians to [Double] degrees
 */
fun Double.toDegrees() = this * 180 / PI