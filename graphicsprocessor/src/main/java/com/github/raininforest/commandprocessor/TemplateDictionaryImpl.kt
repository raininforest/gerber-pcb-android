package com.github.raininforest.commandprocessor

import com.github.raininforest.syntaxparser.api.dictionary.MacroTemplateDictionary
import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.templates.MacroTemplate

/**
 * [MacroTemplateDictionary] implementation
 */
class TemplateDictionaryImpl : MacroTemplateDictionary {
    private val templates = mutableMapOf<String, MacroTemplate>()

    override fun add(item: MacroTemplate) {
        templates[item.name] = item
    }

    override fun get(id: String): MacroTemplate = templates[id]
        ?: throw IllegalStateException("ERROR! No such aperture with id:$id in aperture dictionary!")
}
