package com.example.pastry.ui.home

import androidx.lifecycle.ViewModel
import com.example.pastry.data.remote.model.Product
import com.example.pastry.data.remote.repository.PastryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class HomeViewModel(
    private val repository: PastryRepository,
) : ViewModel() {
    private val _allProducts = MutableStateFlow<List<Product>>(emptyList())
}