package com.berghachi.guidomia.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Collections

class CarTypeConverter {
    @TypeConverter
    fun storedStringToListOfString(data: String?): List<String?>? {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<String?>?>() {}.type
        return gson.fromJson<List<String?>>(data, listType)
    }

    @TypeConverter
    fun ListOfStringToStoredString(myObjects: List<String?>?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }
}
