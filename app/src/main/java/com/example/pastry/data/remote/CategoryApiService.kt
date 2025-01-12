package com.example.pastry.data.remote

import com.example.pastry.data.remote.dto.CategoryDtoItem
import com.example.pastry.data.remote.dto.ProductDtoItem
import retrofit2.http.GET

interface CategoryApiService {

    @GET("https://api.escuelajs.co/api/v1/categories")
    suspend fun getCategories(): List<CategoryDtoItem>
}