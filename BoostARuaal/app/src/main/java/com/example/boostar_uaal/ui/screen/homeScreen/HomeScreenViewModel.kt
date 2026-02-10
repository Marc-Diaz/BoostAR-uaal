package com.example.boostar_uaal.ui.screen.homeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.data.repository.MockProductRepositoryImpl
import com.example.core.entities.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel: ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    init {
        getProducts()
    }
    fun getProducts(){
        viewModelScope.launch {
            _products.value = MockProductRepositoryImpl().getProducts()
            Log.d("HomeScreenViewModel", "getProducts: ${_products.value}")
        }
    }
}