package com.github.raininforest.syntaxparser.impl.utils

import com.github.raininforest.core.pi

/**
 * Math extensions
 *
 * Created by Sergey Velesko on 23.10.2021
 */

/**
 * Converts [Double] degrees to [Double] radians
 */
fun Double.degreesToRadians() = this * pi / 180
