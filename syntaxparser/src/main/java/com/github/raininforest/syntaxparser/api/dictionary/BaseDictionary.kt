package com.github.raininforest.syntaxparser.api.dictionary

/**
 * Created by Sergey Velesko on 09.10.2021
 */
interface BaseDictionary<T> {
    infix fun add(item: T)
    infix fun get(id: String): T
}