package com.example.workshopshopingapp.data.model

/**
 * Data class representing an item added to the user's shopping cart.
 */
data class CartItem(
    val product: Product,
    val quantity: Int = 1
)
