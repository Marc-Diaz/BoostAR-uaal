package com.example.boostar_uaal.ui.screen.fashionScreen

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.BoostArApplication.Companion.likeRepository
import com.example.boostar_uaal.BoostArApplication.Companion.productRepository
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.entities.DropData
import com.example.boostar_uaal.data.models.SortOrder
import com.example.core.entities.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FashionNewsScreenViewmodel: ViewModel() {

    private val _productsTrends = MutableStateFlow<List<Product>>(emptyList())
    val productsTrends: StateFlow<List<Product>> = _productsTrends.asStateFlow()


    fun initializeFashionNews(){
        loadProductsTrends()
    }

    fun loadProductsTrends(){
        viewModelScope.launch(Dispatchers.IO) {
            _productsTrends.value = productRepository.getProducts(
                sortMode = SortOrder.TRENDS
            )
        }
    }

    fun toggleLike(productId: Int) {
        viewModelScope.launch {
            likeRepository.toggleLike(productId)
        }
    }

    val dropsHardcodeados = listOf(
        DropData(
            id = 1,
            imageRes = R.drawable.camiseta_stranger,
            status = "DROP DEL MOMENTO",
            statusColor = Color(0xFF007AFF), // Azul
            title = "Nuevas zapatillas Ari Jordun.",
            collection = "Pruébalo y reserva antes de tiempo.",
            // Ejemplo: 2 días a partir de ahora
            targetTimestamp = System.currentTimeMillis() + (2 * 24 * 60 * 60 * 1000),
            isNotified = false
        ),
        DropData(
            id = 2,
            imageRes = R.drawable.tejano_flores_azules,
            status = "PRÓXIMAMENTE",
            statusColor = Color.Black,
            title = "Chaqueta de cuero biker.",
            collection = "Colección de otoño exclusiva.",
            targetTimestamp = System.currentTimeMillis() + (5 * 60 * 60 * 1000),
            isNotified = true
        )
    )
}