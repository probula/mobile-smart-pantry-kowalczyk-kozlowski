package com.example.mobile_smart_pantry_project_iv.models

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Product(
    val uuid: String = UUID.randomUUID().toString(),
    val name: String,
    var quantity: Int,
    val category: String,
    val imageRef: String
)
