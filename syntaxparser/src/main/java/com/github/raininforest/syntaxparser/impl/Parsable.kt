package com.github.raininforest.syntaxparser.impl

import com.github.raininforest.syntaxparser.api.GerberCommand

/**
 * Created by Sergey Velesko on 26.09.2021
 */
internal interface Parsable {
    fun parse(stringList: List<String>, lineIndexHandler: LineIndexHandler): GerberCommand
}