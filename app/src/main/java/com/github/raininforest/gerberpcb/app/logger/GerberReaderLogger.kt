package com.github.raininforest.gerberpcb.app.logger

import com.github.raininforest.logger.ILogger
import timber.log.Timber

/**
 * Created by Sergey Velesko on 24.10.2021
 */
class GerberReaderLogger : ILogger {
    override fun e(msg: String) {
        Timber.e(msg)
    }

    override fun d(msg: String) {
        Timber.d(msg)
    }
}
