package com.github.raininforest.syntaxparser.impl.utils

import com.github.raininforest.syntaxparser.impl.commands.aperturedefinition.DnnCommand
import java.lang.StringBuilder

/**
 * String utils for parsing
 *
 * Created by Sergey Velesko on 10.10.2021
 */

/**
 * True if end of file command
 */
internal fun String.detectEndOfFile(): Boolean = this.contains("M02")

/**
 * True if command is comment G04
 */
internal fun String.detectComment(): Boolean = this.startsWith("G04")

/**
 * True if command is extended gerber command
 */
internal fun String.detectExtendedCommand(): Boolean = this.startsWith("%")

/**
 * True if command is D01, D02, D03 or Dnn
 */
internal fun String.detectDCommand(): Boolean =
    this.contains("X") || this.contains("Y") || this.contains("D")

/**
 * True if contains G
 */
internal fun String.detectGCommand(): Boolean = this.contains("G")

/**
 * Detects Dnn command
 */
internal fun String.isDnn(): Boolean = DnnCommand.DNN_PATTERN.matcher(this).find()

/**
 * Detects D01 command in string, that has been already defined as DCommand string.
 */
internal fun String.isDCodeD01(): Boolean = this.contains("D01*") || this.contains("D1*")

/**
 * Detects D02 command in string, that has been already defined as DCommand string.
 */
internal fun String.isDCodeD02(): Boolean = this.contains("D02*") || this.contains("D2*")

/**
 * Detects D03 command in string, that has been already defined as DCommand string.
 */
internal fun String.isDCodeD03(): Boolean = this.contains("D03*") || this.contains("D3*")

/**
 * Parses string value of coordinate to double
 *
 * @param numOfInt number of integer positions (from FS command)
 * @param numOfDec number of decimal positions (from FS command)
 *
 * @return [Double] result
 */
internal fun String.fromCoordinateToDouble(numOfInt: Int, numOfDec: Int): Double {
    val valueBuilder = StringBuilder(this)
    var sign = 0 // count of position for sign (can be 0 or 1)
    if ((valueBuilder[0] == '-') || (valueBuilder[0] == '+')) {
        sign = 1
    }
    val countOfDigitByFormat = numOfInt + numOfDec
    val actualAndFormatCountDifference = countOfDigitByFormat - (this.length - sign)
    if (actualAndFormatCountDifference > 0) {
        for (i in 0 until actualAndFormatCountDifference) {
            valueBuilder.insert(sign, '0')
        }
    }

    valueBuilder.insert(sign + numOfInt, '.')
    return valueBuilder.toString().toDouble()
}
