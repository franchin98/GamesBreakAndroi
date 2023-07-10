package com.example.gamesbreak.ui
import com.google.gson.Gson
import java.net.URLDecoder
import java.net.URLEncoder

object ConversionHelper {
    private val gson = Gson()

    fun <T> toJsonString(obj: T): String {
        return gson.toJson(obj)
    }

    fun <T> fromJsonString(jsonString: String, type: Class<T>): T {
        return gson.fromJson(jsonString, type)
    }

    fun toUrlString(jsonString: String): String {
        return URLEncoder.encode(jsonString, "UTF-8")
    }

    fun fromUrlString(urlString: String): String {
        return URLDecoder.decode(urlString, "UTF-8")
    }
}
