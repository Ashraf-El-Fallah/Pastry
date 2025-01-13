package com.example.pastry.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pastry.data.remote.NetWorkResponseState
import com.example.pastry.data.remote.model.ProductDetails
import com.example.pastry.data.remote.repository.PastryRepository
import com.example.pastry.utils.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val repository: PastryRepository,
) : ViewModel() {
    private val _productDetails = MutableLiveData<ScreenState<ProductDetails>>()
    val productDetails: LiveData<ScreenState<ProductDetails>> = _productDetails

    fun getProductDetailsById(productId: Int) {
        viewModelScope.launch {
            repository.getProductDetailsById(productId).collect { response ->
                when (response) {
                    is NetWorkResponseState.Loading -> _productDetails.postValue(ScreenState.Loading)
                    is NetWorkResponseState.Success -> _productDetails.postValue(
                        ScreenState.Success(
                            response.result
                        )
                    )
                    is NetWorkResponseState.Error -> _productDetails.postValue(ScreenState.Error())

                }
            }
        }
    }
}