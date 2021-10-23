package com.github.raininforest.syntaxparser.impl.utils

/**
 * Extensions for Collection
 *
 * Created by Sergey Velesko on 23.10.2021
 */

/**
 * Get value with specified [index] or returns 0.0, if no such index
 */
fun List<Double>.valueOrZero(index: Int): Double = if (index < size) this[index] else 0.0
