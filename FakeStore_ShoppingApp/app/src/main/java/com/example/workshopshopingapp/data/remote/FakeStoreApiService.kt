package com.example.workshopshopingapp.data.remote

import com.example.workshopshopingapp.data.model.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit Interface defining REST API endpoints for FakeStore API.
 *
 * Base URL: https://fakestoreapi.com/
 *
 * Annotations used:
 * - @GET: Specifies HTTP GET method with relative endpoint path.
 * - @Path: Dynamically substitutes URL placeholders (e.g. {id} or {category}).
 * - suspend: Marks functions as coroutine-friendly so network calls run asynchronously off the main UI thread.
 */
interface FakeStoreApiService {

    /**
     * Fetch all products.
     * Endpoint: GET https://fakestoreapi.com/products
     */
    @GET("products")
    suspend fun getProducts(): Response<List<Product>>

    /**
     * Fetch a specific product by its unique ID.
     * Endpoint: GET https://fakestoreapi.com/products/{id}
     * Example: GET https://fakestoreapi.com/products/1
     */
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") productId: Int): Response<Product>

    /**
     * Fetch all product categories available in FakeStore API.
     * Endpoint: GET https://fakestoreapi.com/products/categories
     */
    @GET("products/categories")
    suspend fun getCategories(): Response<List<String>>

    /**
     * Fetch products belonging to a specific category.
     * Endpoint: GET https://fakestoreapi.com/products/category/{category}
     * Example: GET https://fakestoreapi.com/products/category/jewelery
     */
    @GET("products/category/{category}")
    suspend fun getProductsByCategory(@Path("category") categoryName: String): Response<List<Product>>
}
