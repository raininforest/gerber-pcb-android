package com.github.raininforest.gerberpcb.app

import android.app.Application
import com.github.raininforest.gerberpcb.BuildConfig
import timber.log.Timber

/**
 * Created by Sergey Velesko on 18.07.2021
 */
class GerberReaderApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}