package com.example.pastry.data.remote.repository

import com.example.pastry.data.remote.CategoryApiService
import com.example.pastry.data.remote.NetWorkResponseState
import com.example.pastry.data.remote.ProductApiService
import com.example.pastry.data.remote.dto.mapper.toCategory
import com.example.pastry.data.remote.dto.mapper.toProduct
import com.example.pastry.data.remote.dto.mapper.toProductDetails
import com.example.pastry.data.remote.model.Category
import com.example.pastry.data.remote.model.Product
import com.example.pastry.data.remote.model.ProductDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class PastryRepositoryImpl @Inject constructor(
    private val categoryApiService: CategoryApiService,
    private val productApiService: ProductApiService,
) : PastryRepository {
    override suspend fun getProducts(): Flow<NetWorkResponseState<List<Product>>> = flow {
        emit(NetWorkResponseState.Loading)
        try {
            val response = productApiService.getProducts()
            val products = response.map { it.toProduct() }
            emit(NetWorkResponseState.Success(products))
        } catch (e: Exception) {
            emit(
                NetWorkResponseState.Error(
                    errorMessageResId = null,
                    exception = e,
                    statusCode = null
                )
            )
        }
    }

    override suspend fun getProductDetailsById(productId: Int?): Flow<NetWorkResponseState<ProductDetails>> =
        flow {
            emit(NetWorkResponseState.Loading)
            try {
                val response = productApiService.getProducts()
                val productDetails = response.find { it.id == productId }?.toProductDetails()

                if (productDetails != null) {
                    emit(NetWorkResponseState.Success(productDetails))
                } else {
                    return@flow
                }
            } catch (e: Exception) {
                emit(
                    NetWorkResponseState.Error(
                        errorMessageResId = null,
                        exception = e,
                        statusCode = null
                    )
                )
            }
        }

    override suspend fun getCategories(): Flow<NetWorkResponseState<List<Category>>> = flow {
        emit(NetWorkResponseState.Loading)
        try {
            val response = categoryApiService.getCategories()
            val categories = response.map { it.toCategory() }
            emit(NetWorkResponseState.Success(categories)) // Emit success state with data
        } catch (e: Exception) {
            emit(
                NetWorkResponseState.Error(
                    errorMessageResId = null,
                    exception = e,
                    statusCode = null
                )
            )
        }
    }

}

