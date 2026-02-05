package com.example.boostar_uaal.ui.screen.feedScreen


import com.example.boostar_uaal.data.repository.ProductRepositoryImpl
import com.example.core.entities.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel

class FeedScreenViewModel : ViewModel() {

    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product

    fun getProduct(productId: Int) {
        val product = ProductRepositoryImpl().getMockProductById(productId)
        _product.value = product
    }
}