package com.sample.androidexam.core.platform

import android.content.Context

class Cache(store: String, context: Context) : PrefStore(context, store), PrefStoreContract {

    override fun setInt(key: String, value: Int) {
        batchApply { it.putInt(key, value) }
    }

    override fun getInt(key: String, default: Int): Int {
        return preferences.getInt(key, default)
    }

    override fun setBool(key: String, value: Boolean) {
        batchApply { it.putBoolean(key, value) }
    }

    override fun getBool(key: String, default: Boolean): Boolean {
        return preferences.getBoolean(key, default)
    }

    override fun setString(key: String, value: String?) {
        batchApply { it.putString(key, value) }
    }

    override fun getString(key: String): String? {
        return preferences.getString(key, null)
    }

    override fun setFloat(key: String, value: Float) {
        batchApply { it.putFloat(key, value) }
    }

    override fun getFloat(key: String, default: Float): Float {
        return preferences.getFloat(key, default)
    }

    override fun clearAll() = clearStore()
}

interface PrefStoreContract {
    fun setString(key: String, value: String?)
    fun getString(key: String): String?

    fun setBool(key: String, value: Boolean)
    fun getBool(key: String, default: Boolean): Boolean

    fun setInt(key: String, value: Int)
    fun getInt(key: String, default: Int): Int

    fun setFloat(key: String, value: Float)
    fun getFloat(key: String, default: Float): Float

    fun clearAll()
}
