package com.github.raininforest.syntaxparser.impl

import com.github.raininforest.logger.Logger
import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.SyntaxParser
import com.github.raininforest.syntaxparser.api.graphicsstate.CoordinateFormat
import com.github.raininforest.syntaxparser.impl.commands.M02Command
import com.github.raininforest.syntaxparser.impl.commands.aperturedefinition.ADCommand
import com.github.raininforest.syntaxparser.impl.commands.aperturedefinition.DnnCommand
import com.github.raininforest.syntaxparser.impl.commands.aperturemacro.AMCommand
import com.github.raininforest.syntaxparser.impl.commands.aperturetransformation.LMCommand
import com.github.raininforest.syntaxparser.impl.commands.aperturetransformation.LPCommand
import com.github.raininforest.syntaxparser.impl.commands.aperturetransformation.LRCommand
import com.github.raininforest.syntaxparser.impl.commands.aperturetransformation.LSCommand
import com.github.raininforest.syntaxparser.impl.commands.coordinate.FSCommand
import com.github.raininforest.syntaxparser.impl.commands.coordinate.MOCommand
import com.github.raininforest.syntaxparser.impl.commands.deprecated.*
import com.github.raininforest.syntaxparser.impl.commands.inerpolationstate.*
import com.github.raininforest.syntaxparser.impl.commands.operations.D01Command
import com.github.raininforest.syntaxparser.impl.commands.operations.D02Command
import com.github.raininforest.syntaxparser.impl.commands.operations.D03Command
import com.github.raininforest.syntaxparser.impl.commands.operations.DOperationCommand
import com.github.raininforest.syntaxparser.impl.commands.regionstate.G36Command
import com.github.raininforest.syntaxparser.impl.commands.regionstate.G37Command
import com.github.raininforest.syntaxparser.impl.exceptions.InvalidGerberException
import com.github.raininforest.syntaxparser.impl.exceptions.WrongCommandFormatException
import com.github.raininforest.syntaxparser.impl.utils.*

/**
 * [SyntaxParser] implementation
 *
 * Created by Sergey Velesko on 14.10.2021
 */
class SyntaxParserImpl(private val gerberValidator: GerberValidator) : SyntaxParser {

    override fun parse(stringList: List<String>, name: String): List<GerberCommand> =
        try {
            if (gerberValidator isGerberValid stringList) {
                parseFile(stringList, name).also { Logger.d("command count: ${it.size}") }
            } else {
                Logger.e("Gerber $name is invalid")
                throw InvalidGerberException()
            }
        } catch (e: Throwable) {
            Logger.e(e.message.orEmpty())
            throw e
        }

    private fun parseFile(stringList: List<String>, name: String): List<GerberCommand> {
        Logger.d("Start parsing file $name")
        val commandList = mutableListOf<GerberCommand>()

        val lineNumberHandler = LineNumberHandler(stringList.size - 1)
        try {
            val coordinateFormat = CoordinateFormat(3, 6)
            val currentDCodeHolder = CurrentDCodeHolder()

            while (!lineNumberHandler.isEnd) {
                val currentString = stringList[lineNumberHandler.lineNumber]

                when {
                    currentString.detectComment() -> {
                        // ignore
                    }
                    currentString.detectExtendedCommand() -> {
                        val extendedCommand = currentString
                            .substring(
                                START_INDEX_OF_EXTENDED_COMMAND,
                                END_INDEX_OF_EXTENDED_COMMAND
                            )
                        commandList
                            .add(
                                parseExtendedCommand(
                                    extendedCommand,
                                    stringList,
                                    lineNumberHandler,
                                    coordinateFormat
                                )
                            )
                    }
                    currentString.detectDCommand() -> {
                        // for deprecated practise support (g command in d command)
                        if (currentString.removeDeprecationG54().detectGCommand()) {
                            addGCommand(currentString, lineNumberHandler.lineNumber, commandList)
                        }

                        coordinateFormat.let {
                            val command = parseDCommand(
                                currentString.removeDeprecationG54(),
                                lineNumberHandler.lineNumber,
                                coordinateFormat,
                                currentDCodeHolder.currentDCode
                            )
                            commandList.add(command)
                            currentDCodeHolder.currentDCode = when (command) {
                                is D01Command -> DOperationCommand.DCode.D01
                                is D02Command -> DOperationCommand.DCode.D02
                                is D03Command -> DOperationCommand.DCode.D03
                                else -> DOperationCommand.DCode.D01
                            }
                        }
                    }
                    currentString.detectGCommand() -> {
                        addGCommand(currentString, lineNumberHandler.lineNumber, commandList)
                    }
                    currentString.detectEndOfFile() -> {
                        commandList.add(M02Command.parse(stringList, lineNumberHandler))
                        Logger.d("End of file $name")
                    }
                }
                lineNumberHandler.increment()
            }
        } catch (e: Throwable) {
            Logger.e("Parsing file $name failed!")
            throw WrongCommandFormatException(
                line = lineNumberHandler.lineNumber,
                e.localizedMessage
            )
        }

        Logger
        return commandList
    }

    private fun addGCommand(currentString: String, lineNumber: Int, commandList: MutableList<GerberCommand>) {
        val gCommand = currentString
            .substring(START_INDEX_OF_G_COMMAND, END_INDEX_OF_G_COMMAND)
        commandList.add(parseGCommand(gCommand, lineNumber))
    }

    private fun parseGCommand(
        gCommand: String,
        lineNumber: Int
    ): GerberCommand =
        when (gCommand) {
            "G01" -> G01Command(lineNumber)
            "G02" -> G02Command(lineNumber)
            "G03" -> G03Command(lineNumber)
            "G36" -> G36Command(lineNumber)
            "G37" -> G37Command(lineNumber)
            "G54" -> G54Command(lineNumber)
            "G55" -> G55Command(lineNumber)
            "G70" -> G70Command(lineNumber)
            "G71" -> G71Command(lineNumber)
            "G74" -> G74Command(lineNumber)
            "G75" -> G75Command(lineNumber)
            "G90" -> G90Command(lineNumber)
            "G91" -> G91Command(lineNumber)
            else -> throw WrongCommandFormatException(
                lineNumber,
                "There is no valid G-code gerber command but 'G' was found"
            )
        }

    private fun parseDCommand(
        currentString: String,
        lineNumber: Int,
        coordinateFormat: CoordinateFormat,
        currentDCode: DOperationCommand.DCode
    ): GerberCommand =
        when {
            currentString.isDnn() -> DnnCommand.parse(currentString, lineNumber)
            currentString.isDCodeD01(currentDCode) -> D01Command.parse(
                currentString = currentString,
                lineNumber = lineNumber,
                coordinateFormat = coordinateFormat
            )
            currentString.isDCodeD02(currentDCode) -> D02Command.parse(
                currentString = currentString,
                lineNumber = lineNumber,
                coordinateFormat = coordinateFormat
            )
            currentString.isDCodeD03(currentDCode) -> D03Command.parse(
                currentString = currentString,
                lineNumber = lineNumber,
                coordinateFormat = coordinateFormat
            )
            else -> throw WrongCommandFormatException(
                lineNumber,
                "There is no valid D-code gerber command but 'D' || 'X' || 'Y' was found on line $lineNumber"
            )
        }

    private fun parseExtendedCommand(
        extendedCommand: String,
        stringList: List<String>,
        lineNumberHandler: LineNumberHandler,
        coordinateFormat: CoordinateFormat
    ): GerberCommand =
        when (extendedCommand) {
            "FS" -> {
                val command = (FSCommand.parse(stringList, lineNumberHandler) as FSCommand)
                coordinateFormat.integerCount = command.numOfInteger
                coordinateFormat.decimalCount = command.numOfDecimal
                command
            }
            "MO" -> MOCommand.parse(stringList, lineNumberHandler)
            "AD" -> ADCommand.parse(stringList, lineNumberHandler)
            "AM" -> AMCommand.parse(stringList, lineNumberHandler)
            "LP" -> LPCommand.parse(stringList, lineNumberHandler)
            "LS" -> LSCommand.parse(stringList, lineNumberHandler)
            "LR" -> LRCommand.parse(stringList, lineNumberHandler)
            "LM" -> LMCommand.parse(stringList, lineNumberHandler)
            // deprecated
            "AS" -> ASCommand(lineNumberHandler.lineNumber)
            "IN" -> INCommand(lineNumberHandler.lineNumber)
            "IP" -> IPCommand(lineNumberHandler.lineNumber)
            "IR" -> IRCommand(lineNumberHandler.lineNumber)
            "LN" -> LNCommand(lineNumberHandler.lineNumber)
            "IC" -> ICASCommand(lineNumberHandler.lineNumber)
            "OF" -> OFCommand(lineNumberHandler.lineNumber)
            "MI" -> MICommand(lineNumberHandler.lineNumber)
            "SF" -> SFCommand(lineNumberHandler.lineNumber)
            else -> throw WrongCommandFormatException(
                lineNumberHandler.lineNumber,
                "There is no valid extended gerber command but '%' was found on line ${lineNumberHandler.lineNumber}"
            )
        }

    companion object {
        private const val START_INDEX_OF_EXTENDED_COMMAND = 1
        private const val END_INDEX_OF_EXTENDED_COMMAND = 3
        private const val START_INDEX_OF_G_COMMAND = 0
        private const val END_INDEX_OF_G_COMMAND = 3
    }
}