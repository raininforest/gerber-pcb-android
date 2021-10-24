package com.github.raininforest.logger

/**
 * Singleton logger object.
 * Should be initialized in Application class with [init] method.
 */
object Logger {

    private var logger: ILogger? = null

    fun init(logger: ILogger) {
        this.logger = logger
    }

    /**
     * Logs error message
     */
    fun e(msg: String) = logger?.e(msg)

    /**
     * Logs debug message
     */
    fun d(msg: String) = logger?.d(msg)
}