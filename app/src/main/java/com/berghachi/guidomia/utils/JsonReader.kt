package com.berghachi.guidomia.utils

import android.content.Context
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream
import kotlin.reflect.KClass

object JsonReader {
    private val gson = Gson()
    fun <T : Any> readFile(context: Context, fileName: String, clazz: KClass<T>): T? {
        val json: String?
        val newObject: T?
        try {
            val inputStream: InputStream = context.assets!!.open(fileName)
            json = inputStream.bufferedReader().use { it.readText() }
            newObject = gson.fromJson(json, clazz.java)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return newObject
    }
}