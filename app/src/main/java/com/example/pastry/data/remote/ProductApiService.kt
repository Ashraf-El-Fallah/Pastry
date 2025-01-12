package com.example.pastry.data.remote

import com.example.pastry.data.remote.dto.ProductDtoItem
import retrofit2.http.GET

interface ProductApiService {

    @GET("products")
    suspend fun getProducts(): List<ProductDtoItem>
}

