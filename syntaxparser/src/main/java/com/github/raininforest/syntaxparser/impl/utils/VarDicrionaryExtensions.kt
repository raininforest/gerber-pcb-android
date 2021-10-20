package com.github.raininforest.syntaxparser.impl.utils

/**
 * Var dictionary
 *
 * Created by Sergey Velesko on 20.10.2021
 */
fun List<Double>.toMapDictionary(): MutableMap<Int, Double> {
    val varDictionary = mutableMapOf<Int, Double>()
    indices.forEach {
        varDictionary[it + 1] = this[it]
    }
    return varDictionary
}
