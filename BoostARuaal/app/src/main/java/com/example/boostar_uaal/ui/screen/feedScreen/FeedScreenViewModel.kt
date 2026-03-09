package com.example.boostar_uaal.ui.screen.feedScreen


import android.content.Context
import android.util.Log
import com.example.core.entities.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.BoostArApplication
import com.example.boostar_uaal.core.entities.ProductDetail
import com.example.boostar_uaal.core.repository.ProductRepository
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FeedScreenViewModel : ViewModel() {
//define el viewModel para el feed screen
    private val repository: ProductRepository = BoostArApplication.productRepository
    private val _product = MutableStateFlow<ProductDetail?>(null)
    val product: StateFlow<ProductDetail?> = _product

    fun getProduct(productId: Int) {
        viewModelScope.launch {
            val product = repository.getProductById(productId)
            _product.value = product
        }
    }

    fun onTryArClick(context: Context) {
        Log.d("HomeScreenViewModel", "El usuario quiere probar la cámara AR")
        // BoostArApplication.unityHandler.sendClothingToUnity(context, "${product.value}", "${product.value}" )

    }


}
