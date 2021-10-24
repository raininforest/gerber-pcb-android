package com.github.raininforest.syntaxparser.impl.utils

/**
 * Var dictionary extensions
 *
 * Created by Sergey Velesko on 20.10.2021
 */

/**
 * Constructs [Map] dictionary from [List] of [Double] values
 * where key = list_index + 1
 */
fun List<Double>.toMapDictionary(): MutableMap<Int, Double> {
    val varDictionary = mutableMapOf<Int, Double>()
    indices.forEach {
        varDictionary[it + 1] = this[it]
    }
    return varDictionary
}
