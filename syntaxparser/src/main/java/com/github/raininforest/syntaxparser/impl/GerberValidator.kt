package com.github.raininforest.syntaxparser.impl

import com.github.raininforest.logger.Logger
import com.github.raininforest.syntaxparser.impl.commands.coordinate.FSCommand
import com.github.raininforest.syntaxparser.impl.commands.coordinate.MOCommand

/**
 * Validates gerber by checking MO and FS commands.
 *
 * Created by Sergey Velesko on 15.10.2021
 */
internal class GerberValidator(private val notCommentStringsCountToFindFsAndMo: Int = 10) {

    /**
     * If [notCommentStringsCountToFindFsAndMo] commands parsed, and FS and MO are not found,
     * gerber is invalid
     */
    infix fun isGerberValid(stringList: List<String>): Boolean {
        Logger.d("Start validating gerber")

        var isFileValid = false
        // line index handler
        val lineNumberHandler = LineNumberHandler(stringList.size - 1)

        // counters for MO and FS commands
        var moCounter = 0
        var fsCounter = 0
        // counter how many commands were before FS/MO
        var notCommentStringCounter = 0

        while (notCommentStringCounter < notCommentStringsCountToFindFsAndMo) {
            val currentString = stringList[lineNumberHandler.lineNumber]
            if (currentString.startsWith("G04")) {
                continue
            }

            val matcherFS = FSCommand.FS_PATTERN.matcher(currentString)
            if (matcherFS.find()) {
                fsCounter++
            }

            val matcherMO = MOCommand.MO_PATTERN.matcher(currentString)
            if (matcherMO.find()) {
                moCounter++
            }

            if ((fsCounter == 1) && (moCounter == 1)) {
                isFileValid = true
                break
            }

            notCommentStringCounter++
            lineNumberHandler.increment()
        }

        Logger.d("Finished gerber validating. isFileValid=$isFileValid")
        return isFileValid
    }
}
