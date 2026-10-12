package com.example.workshopshopingapp.data.repository

import com.example.workshopshopingapp.data.model.Product
import com.example.workshopshopingapp.data.remote.FakeStoreApiService
import com.example.workshopshopingapp.data.remote.RetrofitInstance
import com.example.workshopshopingapp.util.NetworkResult
import retrofit2.Response
import java.io.IOException

/**
 * Repository class responsible for interacting with FakeStore REST API and returning
 * NetworkResult wrapper states.
 */
class ProductRepository(
    private val apiService: FakeStoreApiService = RetrofitInstance.apiService
) {

    /**
     * Fetch all products from API.
     */
    suspend fun getProducts(): NetworkResult<List<Product>> {
        return safeApiCall { apiService.getProducts() }
    }

    /**
     * Fetch a single product by ID from API.
     */
    suspend fun getProductById(id: Int): NetworkResult<Product> {
        return safeApiCall { apiService.getProductById(id) }
    }

    /**
     * Fetch all available product categories.
     */
    suspend fun getCategories(): NetworkResult<List<String>> {
        return safeApiCall { apiService.getCategories() }
    }

    /**
     * Fetch products belonging to a specific category.
     */
    suspend fun getProductsByCategory(category: String): NetworkResult<List<Product>> {
        return safeApiCall { apiService.getProductsByCategory(category) }
    }

    /**
     * Helper wrapper for safe Retrofit API execution with exception handling.
     */
    private suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        return try {
            val response = apiCall()
            if (response.isSuccessful && response.body() != null) {
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error(
                    message = "Server error: ${response.code()} ${response.message()}"
                )
            }
        } catch (e: IOException) {
            NetworkResult.Error(
                message = "Network error: Please check your internet connection.",
                cause = e
            )
        } catch (e: Exception) {
            NetworkResult.Error(
                message = e.localizedMessage ?: "An unexpected error occurred.",
                cause = e
            )
        }
    }
}
