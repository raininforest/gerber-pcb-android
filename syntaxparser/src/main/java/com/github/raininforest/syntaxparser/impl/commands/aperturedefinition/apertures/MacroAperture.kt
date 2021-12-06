package com.github.raininforest.syntaxparser.impl.commands.aperturedefinition.apertures

import com.github.raininforest.syntaxparser.api.Aperture
import com.github.raininforest.syntaxparser.api.CommandProcessor
import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.macrobody.macroprimitives.MacroPrimitive

/**
 * Macro [Aperture]
 *
 * Created by Sergey Velesko on 16.10.2021
 */
data class MacroAperture(
    override val apertureId: String,
    val primitives: List<MacroPrimitive>
) : Aperture {

    override fun flash(processor: CommandProcessor) {
        processor.startMacroFlash()
        primitives.forEach {
            it.draw(processor)
        }
        processor.finishMacroFlash()
    }
}
