package com.github.raininforest.gerberpcb.app

import android.app.Application
import com.github.raininforest.gerberpcb.BuildConfig
import com.github.raininforest.gerberpcb.di.application
import com.github.raininforest.logger.ILogger
import com.github.raininforest.logger.Logger
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 * [Application] class
 *
 * Created by Sergey Velesko on 18.07.2021
 */
class GerberReaderApp : Application() {

    private val logger: ILogger by inject()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GerberReaderApp)
            modules(application)
        }

        initLogger()
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        Logger.init(logger)
    }
}