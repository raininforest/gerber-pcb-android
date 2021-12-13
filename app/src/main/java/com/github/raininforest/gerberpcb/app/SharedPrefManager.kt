package com.github.raininforest.gerberpcb.app

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(private val context: Context) {

    private val prefs = context.getSharedPreferences(PREF_NAME, 0)
    private val editor: SharedPreferences.Editor = prefs.edit()

    fun setRunFirst(runFirst: Boolean) {
        editor.remove(KEY_FIRST_RUN)
        editor.putBoolean(KEY_FIRST_RUN, runFirst)
        editor.apply()
    }

    fun getRunFirst(): Boolean =
        prefs.getBoolean(KEY_FIRST_RUN, DEFAULT_FIRST_RUN_VALUE)

    companion object {
        private const val PREF_NAME = "pref_name"
        private const val KEY_FIRST_RUN = "pref_name"

        private const val DEFAULT_FIRST_RUN_VALUE = false
    }
}