package com.example.workshopshopingapp.util

/**
 * A generic sealed class representing the state of a network request.
 * Useful for handling Loading, Success, and Error states cleanly across the app.
 */
sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error(val message: String, val cause: Throwable? = null) : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()
}
