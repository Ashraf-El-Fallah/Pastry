package com.example.pastry.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pastry.data.remote.NetWorkResponseState
import com.example.pastry.data.remote.model.Category
import com.example.pastry.data.remote.model.Product
import com.example.pastry.data.remote.repository.PastryRepository
import com.example.pastry.utils.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: PastryRepository,
) : ViewModel() {
    private val _allProducts = MutableLiveData<ScreenState<List<Product>>>()
    val allProducts: LiveData<ScreenState<List<Product>>> get() = _allProducts

    private val _allCategories = MutableLiveData<ScreenState<List<Category>>>()
    val allCategories: LiveData<ScreenState<List<Category>>> get() = _allCategories

    private val imageUrls = mutableListOf<String>()


    init {
        getAllProducts()
        getAllCategories()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            repository.getProducts().collectLatest {
                when (it) {
                    is NetWorkResponseState.Loading -> _allProducts.postValue(ScreenState.Loading)
                    is NetWorkResponseState.Success -> {
                        _allProducts.postValue(ScreenState.Success(it.result))
                        it.result.forEach { product ->
                            product.image?.let { it1 -> imageUrls.add(it1) }  // Collect all product images
                        }
                    }

                    is NetWorkResponseState.Error -> _allProducts.postValue(ScreenState.Error())
                }
            }
        }
    }

    fun getRandomImagesForCards(): Pair<String, String> {
        return if (imageUrls.size >= 2) {
            val card1Image = imageUrls.random()
            val card2Image = imageUrls.filter { it != card1Image }.random()
            Pair(card1Image, card2Image)
        } else {
            Pair("default_image_url", "default_image_url")
        }
    }

    private fun getAllCategories() {
        viewModelScope.launch {
            repository.getCategories().collectLatest {
                when (it) {
                    is NetWorkResponseState.Loading -> _allCategories.postValue(ScreenState.Loading)
                    is NetWorkResponseState.Success ->
                        _allCategories.postValue(
                            ScreenState.Success(
                                it.result
                            )
                        )

                    is NetWorkResponseState.Error -> _allCategories.postValue(ScreenState.Error())
                }
            }
        }
    }
}