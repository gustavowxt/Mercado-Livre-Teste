package com.example.mercadolivreteste

data class ProductDetail(
    val id: String,
    val title: String,
    val price: Double,
    val thumbnail: String,
    val description: String?,
    val pictures: List<Picture>
)

data class Picture(
    val url: String
)
