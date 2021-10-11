package com.github.raininforest.syntaxparser.impl.utils

import com.github.raininforest.syntaxparser.impl.commands.aperturedefinition.DnnCommand
import java.lang.StringBuilder

/**
 * String utils for parsing
 *
 * Created by Sergey Velesko on 10.10.2021
 */
fun String.detectDCommand(): Boolean =
    this.contains("X") || this.contains("Y") || this.contains("D")

fun String.isDnn(): Boolean = DnnCommand.DNN_PATTERN.matcher(this).find()

fun String.isD01(): Boolean = this.contains("D01")

fun String.isD02(): Boolean = this.contains("D02")

fun String.isD03(): Boolean = this.contains("D03")

fun String.fromCoordinateToDouble(numOfInt: Int, numOfDec: Int): Double {
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
