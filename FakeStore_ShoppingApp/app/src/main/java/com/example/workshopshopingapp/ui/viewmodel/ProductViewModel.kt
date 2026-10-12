package com.example.workshopshopingapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workshopshopingapp.data.model.CartItem
import com.example.workshopshopingapp.data.model.Product
import com.example.workshopshopingapp.data.repository.ProductRepository
import com.example.workshopshopingapp.ui.state.ProductDetailUiState
import com.example.workshopshopingapp.ui.state.ProductsUiState
import com.example.workshopshopingapp.util.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel managing UI states, REST API interactions, and Shopping Cart functionality.
 */
class ProductViewModel(
    private val repository: ProductRepository = ProductRepository()
) : ViewModel() {

    private val _productsUiState = MutableStateFlow<ProductsUiState>(ProductsUiState.Loading)
    val productsUiState: StateFlow<ProductsUiState> = _productsUiState.asStateFlow()

    private val _detailUiState = MutableStateFlow<ProductDetailUiState>(ProductDetailUiState.Loading)
    val detailUiState: StateFlow<ProductDetailUiState> = _detailUiState.asStateFlow()

    // Shopping Cart State
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    init {
        loadInitialData()
    }

    /**
     * Fetch products list and categories concurrently from FakeStore API.
     */
    fun loadInitialData() {
        viewModelScope.launch {
            _productsUiState.value = ProductsUiState.Loading

            val categoriesResult = repository.getCategories()
            val categoriesList = mutableListOf("All")
            if (categoriesResult is NetworkResult.Success) {
                categoriesList.addAll(categoriesResult.data)
            }

            when (val result = repository.getProducts()) {
                is NetworkResult.Success -> {
                    _productsUiState.value = ProductsUiState.Success(
                        rawProducts = result.data,
                        filteredProducts = result.data,
                        categories = categoriesList,
                        selectedCategory = "All",
                        searchQuery = ""
                    )
                }
                is NetworkResult.Error -> {
                    _productsUiState.value = ProductsUiState.Error(result.message)
                }
                is NetworkResult.Loading -> {
                    _productsUiState.value = ProductsUiState.Loading
                }
            }
        }
    }

    /**
     * Filter product list by category.
     */
    fun selectCategory(category: String) {
        val currentState = _productsUiState.value
        if (currentState is ProductsUiState.Success) {
            val updatedFiltered = applyFilterAndSearch(
                allProducts = currentState.rawProducts,
                category = category,
                query = currentState.searchQuery
            )
            _productsUiState.update {
                currentState.copy(
                    selectedCategory = category,
                    filteredProducts = updatedFiltered
                )
            }
        }
    }

    /**
     * Filter product list by search query.
     */
    fun onSearchQueryChange(query: String) {
        val currentState = _productsUiState.value
        if (currentState is ProductsUiState.Success) {
            val updatedFiltered = applyFilterAndSearch(
                allProducts = currentState.rawProducts,
                category = currentState.selectedCategory,
                query = query
            )
            _productsUiState.update {
                currentState.copy(
                    searchQuery = query,
                    filteredProducts = updatedFiltered
                )
            }
        }
    }

    /**
     * Fetch detail for a single product by ID (demonstrating GET /products/{id}).
     */
    fun loadProductDetail(productId: Int) {
        viewModelScope.launch {
            _detailUiState.value = ProductDetailUiState.Loading
            when (val result = repository.getProductById(productId)) {
                is NetworkResult.Success -> {
                    _detailUiState.value = ProductDetailUiState.Success(result.data)
                }
                is NetworkResult.Error -> {
                    _detailUiState.value = ProductDetailUiState.Error(result.message)
                }
                is NetworkResult.Loading -> {
                    _detailUiState.value = ProductDetailUiState.Loading
                }
            }
        }
    }

    // ==================== Shopping Cart Functions ====================

    fun addToCart(product: Product, quantityToAdd: Int = 1) {
        _cartItems.update { currentList ->
            val existingIndex = currentList.indexOfFirst { it.product.id == product.id }
            if (existingIndex != -1) {
                currentList.toMutableList().apply {
                    val existingItem = this[existingIndex]
                    this[existingIndex] = existingItem.copy(quantity = existingItem.quantity + quantityToAdd)
                }
            } else {
                currentList + CartItem(product = product, quantity = quantityToAdd)
            }
        }
    }

    fun updateCartQuantity(productId: Int, delta: Int) {
        _cartItems.update { currentList ->
            currentList.mapNotNull { item ->
                if (item.product.id == productId) {
                    val newQty = item.quantity + delta
                    if (newQty > 0) item.copy(quantity = newQty) else null
                } else {
                    item
                }
            }
        }
    }

    fun removeFromCart(productId: Int) {
        _cartItems.update { currentList ->
            currentList.filterNot { it.product.id == productId }
        }
    }

    fun clearCart() {
        _cartItems.value = emptyList()
    }

    private fun applyFilterAndSearch(
        allProducts: List<Product>,
        category: String,
        query: String
    ): List<Product> {
        return allProducts.filter { product ->
            val matchesCategory = (category == "All" || product.category.equals(category, ignoreCase = true))
            val matchesSearch = query.isBlank() ||
                    product.title.contains(query, ignoreCase = true) ||
                    product.description.contains(query, ignoreCase = true)
            matchesCategory && matchesSearch
        }
    }
}
