package com.github.raininforest.gerberpcb.di

import com.github.raininforest.gerberpcb.app.GerberReaderLogger
import com.github.raininforest.logger.ILogger
import org.koin.dsl.module

/**
 * Koin modules for DI
 *
 * Created by Sergey Velesko on 24.10.2021
 */
val application = module {
    single<ILogger> { GerberReaderLogger() }
}