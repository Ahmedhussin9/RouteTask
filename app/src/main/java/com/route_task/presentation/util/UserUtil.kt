package com.jawdah.presentation.util

import android.content.Context
import android.content.SharedPreferences


object UserUtil {

    private lateinit var sharedPreferences: SharedPreferences

    private const val IS_FIRST_TIME = "isFirsTime"


    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("local", Context.MODE_PRIVATE)
    }

    fun saveFirstTime() = sharedPreferences.edit().putBoolean(IS_FIRST_TIME, false).apply()
    fun isFirstTime() = sharedPreferences.getBoolean(IS_FIRST_TIME, true)

}