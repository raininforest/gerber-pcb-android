package com.github.raininforest.gerberpcb.di

import com.github.raininforest.GraphicsProcessor
import com.github.raininforest.GraphicsProcessorImpl
import com.github.raininforest.commandprocessor.CommandProcessorImpl
import com.github.raininforest.gerberfilereader.GerberFileReader
import com.github.raininforest.gerberfilereader.GerberFileReaderImpl
import com.github.raininforest.gerberpcb.app.logger.GerberReaderLogger
import com.github.raininforest.gerberpcb.model.GerberProcessor
import com.github.raininforest.gerberpcb.model.GerberRepository
import com.github.raininforest.gerberpcb.model.GerberRepositoryImpl
import com.github.raininforest.gerberpcb.ui.screens.graphicsscreen.GraphicsViewModel
import com.github.raininforest.gerberpcb.ui.screens.graphicsscreen.gerberview.GerberRenderer
import com.github.raininforest.gerberpcb.ui.screens.graphicsscreen.gerberview.GerberRendererImpl
import com.github.raininforest.gerberpcb.ui.screens.layersscreen.LayersViewModel
import com.github.raininforest.logger.ILogger
import com.github.raininforest.syntaxparser.api.CommandProcessor
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

    viewModel { LayersViewModel(gerberRepository = get()) }
    single<GerberFileReader> { GerberFileReaderImpl(context = get()) }
    single { GerberValidator() }
    single<SyntaxParser> { SyntaxParserImpl(gerberValidator = get()) }
    single<GraphicsProcessor> { GraphicsProcessorImpl() }
    single { GerberProcessor(fileReader = get(), parser = get(), graphicsProcessor = get()) }
    single<GerberRepository> { GerberRepositoryImpl(gerberProcessor = get()) }

    viewModel { GraphicsViewModel(gerberRepository = get()) }
    single<GerberRenderer> { GerberRendererImpl() }
}