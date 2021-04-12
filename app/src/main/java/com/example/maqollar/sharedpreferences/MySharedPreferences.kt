package com.example.maqollar.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

object MySharedPreferences {

    private lateinit var myShared: SharedPreferences
    const val FILE_NAME = "proverbs"
    const val KEY = "theme"


    fun init(context: Context) {
        myShared = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

    }

    fun setTheme(boolean: Boolean) {
        val editor = myShared.edit()
        editor.putBoolean(KEY, boolean).apply()
    }

    fun getTheme(): Boolean {
        return myShared.getBoolean(KEY, false)
    }
}