package com.sample.androidexam.core.platform

import android.content.Context
import android.content.SharedPreferences

/**
 * Preference Store class which will be used to store cache for app
 */
abstract class PrefStore(val context: Context, storeName: String) {
    protected val preferences: SharedPreferences = context.getSharedPreferences(storeName, 0)
    private fun editor() = preferences.edit()

    fun batchApply(callback: (SharedPreferences.Editor) -> Unit) {
        val editor = editor()
        callback(editor)
        editor.apply()
    }
    fun clearStore() = editor().clear().apply()
}