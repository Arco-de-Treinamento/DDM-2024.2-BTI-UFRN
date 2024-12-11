package com.manoelfreitas.imdmarket.product.model

data class Product(
    val productCode: Int,
    val productName: String,
    val productDescription: String,
    val productQuantity: Int,
)