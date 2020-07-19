package com.renanparis.chuckjokes.data.database.converter

import androidx.room.TypeConverter

class ListStringConverter {

    @TypeConverter
    fun listToString(list: List<String>): String {
        var string = ""

        for(value in list) {
            string += "$value,"
        }
        return string
    }

    @TypeConverter
    fun stringTiList(string: String): List<String> {
        return string.split("\\s*,\\s*").toMutableList()
    }
}