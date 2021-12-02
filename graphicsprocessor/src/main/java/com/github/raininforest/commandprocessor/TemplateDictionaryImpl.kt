package com.github.raininforest.commandprocessor

import com.github.raininforest.syntaxparser.api.dictionary.MacroTemplateDictionary
import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.templates.ApertureTemplate

/**
 * [MacroTemplateDictionary] implementation
 */
class TemplateDictionaryImpl : MacroTemplateDictionary {
    private val templates = mutableMapOf<String, ApertureTemplate>()

    override fun add(item: ApertureTemplate) {
        templates[item.name] = item
    }

    override fun get(id: String): ApertureTemplate = templates[id]
        ?: throw IllegalStateException("ERROR! No such aperture with id:$id in aperture dictionary!")
}
