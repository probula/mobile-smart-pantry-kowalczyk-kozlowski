package com.example.mobile_smart_pantry_project_iv.utils

import android.content.Context
import com.example.mobile_smart_pantry_project_iv.models.Product
import kotlinx.serialization.json.Json

object JsonParser {
    fun loadProductsFromRaw(context: Context, resourceId: Int): List<Product> {
        return try {
            val jsonString = context.resources.openRawResource(resourceId)
                .bufferedReader()
                .use { it.readText() }
            Json.decodeFromString(jsonString)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
