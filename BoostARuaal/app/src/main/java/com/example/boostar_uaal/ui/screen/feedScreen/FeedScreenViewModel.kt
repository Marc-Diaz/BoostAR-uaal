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

    private val _feedState = MutableStateFlow<List<Product>>(emptyList())
    val feedState: StateFlow<List<Product>> = _feedState.asStateFlow()


    fun getProduct(productId: Int) {
        viewModelScope.launch {
            val product = repository.getProductById(productId)
            _product.value = product
        }
    }

    private fun loadFeed() {
        viewModelScope.launch {
            val products = repository.getProducts()
            _feedState.value = products
        }
    }


    fun onTryArClick(context: Context) {
        Log.d("HomeScreenViewModel", "El usuario quiere probar la cámara AR")
        // BoostArApplication.unityHandler.sendClothingToUnity(context, "${product.value}", "${product.value}" )

    }

    fun onPartnerClick(productId: Int) {
        // Lógica para ir al perfil del partner/marca
    }

    fun onShareClick(productId: Int) {
        // Lógica para abrir el menú de compartir de Android
    }

    fun toggleLike(productId: Int) {
        // Lógica para dar like y actualizar el corazón a rojo
        //ya esta en el HomeViewmodel
    }

    fun onCarClick(productId: Int) {
        // Lógica para añadir a la cesta
    }

    fun onDetailsClick(productId: Int) {
        // Acción del botón blanco izquierdo (Detalles/Documento)
    }

    fun onTryArClick(productId: Int) {
        // Acción del botón naranja central (Probar ropa en AR)
    }

    fun onQuickPayClick(productId: Int) {
        // Acción del botón amarillo derecho (Pago rápido)
    }


}
