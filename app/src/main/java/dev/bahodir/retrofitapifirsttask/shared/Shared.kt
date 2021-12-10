package dev.bahodir.retrofitapifirsttask.shared

import android.content.Context
import android.content.SharedPreferences

class Shared(var context: Context) {
    internal lateinit var shared: SharedPreferences

    init {
        shared = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
    }

    fun setShared(state: Boolean?) {
        val editor: SharedPreferences.Editor = shared.edit()
        editor.putBoolean("share", state!!)
        editor.commit()
    }

    fun getShared(): Boolean? {
        return shared.getBoolean("share", false)
    }
}