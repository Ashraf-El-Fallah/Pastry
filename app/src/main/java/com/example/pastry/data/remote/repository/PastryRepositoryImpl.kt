package com.example.pastry.data.remote.repository

import com.example.pastry.data.remote.CategoryApiService
import com.example.pastry.data.remote.ProductApiService
import javax.inject.Inject


class PastryRepositoryImpl @Inject constructor(
    private val categoryApiService: CategoryApiService,
    private val productApiService: ProductApiService,
) : PastryRepository {
    suspend fun getProducts(): List<Product> {
        return productApiService.getProducts()
    }

}

