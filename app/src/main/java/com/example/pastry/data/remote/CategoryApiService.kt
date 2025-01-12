package com.example.pastry.data.remote

import com.example.pastry.data.remote.dto.CategoryDtoItem
import retrofit2.http.GET

interface CategoryApiService {

    @GET("api/v1/categories")
    suspend fun getCategories(): List<CategoryDtoItem>
}