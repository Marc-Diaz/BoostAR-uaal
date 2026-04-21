package com.example.boostar_uaal.ui.screen.fashionScreen

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.BoostArApplication.Companion.likeRepository
import com.example.boostar_uaal.BoostArApplication.Companion.productRepository
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.entities.Drop
import com.example.boostar_uaal.core.theme.primaryColor
import com.example.boostar_uaal.data.models.SortOrder
import com.example.core.entities.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FashionNewsScreenViewmodel: ViewModel() {

    private val _productsNewest = MutableStateFlow<List<Product>>(emptyList())
    val productsNewest: StateFlow<List<Product>> = _productsNewest.asStateFlow()


    fun initializeFashionNews(){
        loadNewestProducts()
    }

    fun loadNewestProducts(){
        viewModelScope.launch(Dispatchers.IO) {
            _productsNewest.value = productRepository.getProducts(
                sortMode = SortOrder.NEWEST,
                limit = 4
            )
            Log.d("PRODUCTOS NUEVOS", "${productsNewest.value}")
        }
    }

    fun toggleLike(productId: Int) {
        viewModelScope.launch {
            likeRepository.toggleLike(productId)
        }
    }
    fun refreshLikes(){
        viewModelScope.launch {
            likeRepository.likeStateFlow.collect { likeMap ->
                if (likeMap.isEmpty()) return@collect
                fun applyLikes(list: List<Product>): List<Product> = list.map { p ->
                    val liked = likeMap[p.id] ?: return@map p
                    if (liked == p.isLiked) return@map p
                    p.copy(isLiked = liked, numLikes = p.numLikes + if (liked) 1L else -1L)
                }
                _productsNewest.value = applyLikes(_productsNewest.value)

            }
        }
    }
    val dropsHardcodeados = listOf(
        Drop(
            id = 1,
            imageRes = R.drawable.camiseta_stranger,
            status = "DROP DEL MOMENTO",
            statusColor = primaryColor, // Azul
            title = "Nuevas zapatillas Ari Jordun.",
            collection = "Pruébalo y reserva antes de tiempo.",
            targetTimestamp = System.currentTimeMillis() + (2 * 24 * 60 * 60 * 1000),
            isNotified = false
        ),
        Drop(
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