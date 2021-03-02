package com.example.pokedex.data

import android.content.Context
import java.lang.Exception

class ParseUtils {
    fun parseFromJson(context: Context, fileName: String): String? {
        return try {
            context.assets.open(fileName).bufferedReader().use {reader -> reader.readText() }
        } catch (exception: Exception) {
            exception.printStackTrace()
            null
        }

    }
}