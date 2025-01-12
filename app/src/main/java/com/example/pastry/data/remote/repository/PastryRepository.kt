package com.example.pastry.data.remote.repository

import com.example.pastry.data.remote.NetWorkResponseState
import com.example.pastry.data.remote.model.Category
import com.example.pastry.data.remote.model.Product
import kotlinx.coroutines.flow.Flow

interface PastryRepository {
    suspend fun getProducts(): Flow<NetWorkResponseState<List<Product>>>
    suspend fun getCategories(): Flow<NetWorkResponseState<List<Category>>>
}