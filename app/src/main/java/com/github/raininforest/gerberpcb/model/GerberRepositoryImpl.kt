package com.github.raininforest.gerberpcb.model

import android.net.Uri
import com.github.raininforest.gerberfilereader.GerberFileReader
import com.github.raininforest.syntaxparser.api.GerberCommand
import com.github.raininforest.syntaxparser.api.SyntaxParser

/**
 * [GerberRepository] implementation
 *
 * Created by Sergey Velesko on 18.07.2021
 */
class GerberRepositoryImpl(
    private val fileReader: GerberFileReader,
    private val syntaxParser: SyntaxParser
) : GerberRepository {

    private val gerbers: MutableMap<String, Gerber> = mutableMapOf()

    override suspend fun list(): List<GerberItemUi> =
        listOf(
            GerberItemUi(name = "gerber_topsilk_01.gbr"),
            GerberItemUi(name = "gerber_02.gbr", checked = true),
            GerberItemUi(name = "gerber_03.gbr")
        )


    override suspend fun addItem(fileUri: Uri): Boolean {
        val filename = fileUri.path?.substring(
            fileUri.path?.lastIndexOf('/') ?: 0,
            fileUri.path?.lastIndex ?: 0
        ) ?: "<empty>"

        val listOfCommands: List<GerberCommand> =
            syntaxParser.parse(stringList = fileReader.read(fileUri), name = filename)
        TODO("Not yet implemented")
        return listOfCommands.isNotEmpty()
    }

    override suspend fun removeItem(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun changeItemVisibility(id: String, visibility: Boolean) {
        TODO("Not yet implemented")
    }
}
