package com.example.workshopshopingapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * Data class representing a Product returned from FakeStore API.
 * FakeStore API endpoint: https://fakestoreapi.com/products
 *
 * @SerializedName ensures Kotlin field names map correctly to JSON keys,
 * even if JSON uses different casing or names.
 */
data class Product(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("price")
    val price: Double,

    @SerializedName("description")
    val description: String,

    @SerializedName("category")
    val category: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("rating")
    val rating: Rating? = null
)

/**
 * Data class representing the rating information for a product.
 */
data class Rating(
    @SerializedName("rate")
    val rate: Double,

    @SerializedName("count")
    val count: Int
)
