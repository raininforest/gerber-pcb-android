package com.github.raininforest.commandprocessor

import com.github.raininforest.syntaxparser.api.Aperture
import com.github.raininforest.syntaxparser.api.dictionary.ApertureDictionary
import java.lang.IllegalStateException

/**
 * [ApertureDictionary] implementation
 */
class ApertureDictionaryImpl : ApertureDictionary {
    private val apertures = mutableMapOf<String, Aperture>()

    override fun add(item: Aperture) {
        apertures[item.apertureId] = item
    }

    override fun get(id: String): Aperture =
        apertures[id]
            ?: throw IllegalStateException("ERROR! No such aperture with id:$id in aperture dictionary!")
}