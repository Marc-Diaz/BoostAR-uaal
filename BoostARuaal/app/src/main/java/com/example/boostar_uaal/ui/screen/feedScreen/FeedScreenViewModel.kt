package com.example.boostar_uaal.ui.screen.feedScreen


import com.example.core.entities.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.core.entities.ProductDetail
import com.example.boostar_uaal.data.repository.MockProductRepositoryImpl
import kotlinx.coroutines.launch

class FeedScreenViewModel : ViewModel() {

    private val _product = MutableStateFlow<ProductDetail?>(null)
    val product: StateFlow<ProductDetail?> = _product

    fun getProduct(productId: Int) {
        viewModelScope.launch {
            val product = MockProductRepositoryImpl().getProductById(productId)
            _product.value = product
        }
    }
}