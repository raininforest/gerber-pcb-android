package com.github.raininforest.syntaxparser.impl

import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.SyntaxParser

class SyntaxParserImpl() : SyntaxParser {

    override fun parse(stringList: List<String>): List<GerberCommand> =
        if (isGerberFileValid(stringList)) {
            parseFile(stringList)
        } else {
            emptyList()
            //TODO log error
        }

    private fun isGerberFileValid(stringList: List<String>): Boolean {
        //TODO log start validating file
        TODO("Not yet implemented")
        //TODO log finish validating
    }

    private fun parseFile(stringList: List<String>): List<GerberCommand> {
        //TODO log start parsing file
        TODO("Not yet implemented")
        //TODO finish parsing
    }
}