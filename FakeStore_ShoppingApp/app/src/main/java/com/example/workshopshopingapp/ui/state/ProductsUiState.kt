package com.example.workshopshopingapp.ui.state

import com.example.workshopshopingapp.data.model.Product

/**
 * UI State for Products List screen.
 */
sealed interface ProductsUiState {
    object Loading : ProductsUiState

    data class Success(
        val rawProducts: List<Product> = emptyList(),
        val filteredProducts: List<Product> = emptyList(),
        val categories: List<String> = emptyList(),
        val selectedCategory: String = "All",
        val searchQuery: String = ""
    ) : ProductsUiState

    data class Error(val message: String) : ProductsUiState
}

/**
 * UI State for Product Detail screen.
 */
sealed interface ProductDetailUiState {
    object Loading : ProductDetailUiState

    data class Success(
        val product: Product
    ) : ProductDetailUiState

    data class Error(val message: String) : ProductDetailUiState
}
