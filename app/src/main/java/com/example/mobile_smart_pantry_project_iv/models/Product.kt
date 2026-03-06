package com.example.mobile_smart_pantry_project_iv.models

import java.util.UUID

data class Product(
    val uuid: String = UUID.randomUUID().toString(),
    val name: String,
    val quantity: Int,
    val category: String,
    val imageRef: String

)