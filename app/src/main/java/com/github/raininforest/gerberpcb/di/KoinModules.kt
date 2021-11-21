package com.github.raininforest.gerberpcb.di

import com.github.raininforest.gerberfilereader.GerberFileReader
import com.github.raininforest.gerberfilereader.GerberFileReaderImpl
import com.github.raininforest.gerberpcb.logger.GerberReaderLogger
import com.github.raininforest.gerberpcb.model.GerberProcessor
import com.github.raininforest.gerberpcb.model.GerberRepository
import com.github.raininforest.gerberpcb.model.GerberRepositoryImpl
import com.github.raininforest.gerberpcb.ui.layers.LayersViewModel
import com.github.raininforest.logger.ILogger
import com.github.raininforest.syntaxparser.api.SyntaxParser
import com.github.raininforest.syntaxparser.impl.GerberValidator
import com.github.raininforest.syntaxparser.impl.SyntaxParserImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin modules for DI
 *
 * Created by Sergey Velesko on 24.10.2021
 */
val application = module {
    single<ILogger> { GerberReaderLogger() }

    single<GerberFileReader> { GerberFileReaderImpl(context = get()) }
    single { GerberValidator() }
    single<SyntaxParser> { SyntaxParserImpl(gerberValidator = get()) }

    single { GerberProcessor(fileReader = get(), parser = get()) }

    single<GerberRepository> { GerberRepositoryImpl(gerberProcessor = get()) }

    viewModel { LayersViewModel(gerberRepository = get()) }
}