package com.example.workshopshopingapp

import com.example.workshopshopingapp.data.model.Product
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

/**
 * Unit test verifying Gson JSON deserialization for FakeStore API Product response.
 */
class ProductJsonMappingTest {

    @Test
    fun testFakeStoreJsonDeserialization() {
        val json = """
            {
                "id": 1,
                "title": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                "price": 109.95,
                "description": "Your perfect pack for everyday use and walks in the forest.",
                "category": "men's clothing",
                "image": "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                "rating": {
                    "rate": 3.9,
                    "count": 120
                }
            }
        """.trimIndent()

        val gson = Gson()
        val product = gson.fromJson(json, Product::class.java)

        assertNotNull(product)
        assertEquals(1, product.id)
        assertEquals("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops", product.title)
        assertEquals(109.95, product.price, 0.01)
        assertEquals("men's clothing", product.category)
        assertNotNull(product.rating)
        assertEquals(3.9, product.rating?.rate ?: 0.0, 0.01)
        assertEquals(120, product.rating?.count ?: 0)
    }
}
