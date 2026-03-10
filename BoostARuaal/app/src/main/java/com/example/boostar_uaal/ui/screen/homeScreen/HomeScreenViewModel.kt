package com.example.boostar_uaal.ui.screen.homeScreen

import android.content.Context
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.BoostArApplication
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.entities.DropData
import com.example.boostar_uaal.core.entities.PartnerData
import com.example.boostar_uaal.core.repository.LikeRepository
import com.example.boostar_uaal.core.repository.PartnerRepository
import com.example.boostar_uaal.ui.screen.homeScreen.components.CollabData
import com.example.boostar_uaal.ui.screen.homeScreen.components.HeroBannerData
import com.example.core.entities.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private val productRepository = BoostArApplication.productRepository
    private val likeRepository: LikeRepository = BoostArApplication.likeRepository
    private val partnerRepository: PartnerRepository = BoostArApplication.partnerRepository
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()
    private val _banners = MutableStateFlow<List<HeroBannerData>>(emptyList())
    val banners: StateFlow<List<HeroBannerData>> = _banners.asStateFlow()
    private val _collabs = MutableStateFlow<List<CollabData>>(emptyList())
    val collabs: StateFlow<List<CollabData>> = _collabs.asStateFlow()
    private val _partners = MutableStateFlow<List<PartnerData>>(emptyList())
    val partners: StateFlow<List<PartnerData>> = _partners.asStateFlow()
    private val _drops = MutableStateFlow<List<DropData>>(emptyList())
    val drops: StateFlow<List<DropData>> = _drops.asStateFlow()

    init {
        loadProducts()
        loadBanners()
        loadCollabs()
        loadPartners()
        loadDrops()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _products.value = productRepository.getProducts()
        }
    }

    private fun loadBanners() {
        _banners.value = listOf(
            HeroBannerData(
                imageRes = R.drawable.home_hero,
                label = "LOOK DESTACADO",
                title = "El minimalismo\nurbano vuelve.",
                subtitle = "Descubre el poder del estilo sencillo."
            ),
            HeroBannerData(
                imageRes = R.drawable.carrusel_auth_2,
                label = "NUEVA COLECCIÓN",
                title = "Colores vibrantes\npara primavera.",
                subtitle = "Atrévete con lo nuevo de temporada."
            ),
            HeroBannerData(
                imageRes = R.drawable.home_hero,
                label = "EXCLUSIVIDAD",
                title = "Realidad Aumentada\nen tus manos.",
                subtitle = "Pruébate la ropa sin salir de casa."
            )
        )
    }

    fun onTryArClick(context: Context) {
        Log.d("HomeScreenViewModel", "El usuario quiere probar la cámara AR")
        BoostArApplication.unityHandler.sendClothingToUnity(context, "hola", "url")

    }


    private fun loadCollabs() {
        // En el futuro esto vendrá base de datos (Supabase)
        _collabs.value = listOf(
            CollabData(
                id = 1,
                backgroundImageRes = R.drawable.colab_3,
                brandTop = "PULL&BEAR",
                brandBottomRes = R.drawable.logo_stranger
            ),
            CollabData(
                id = 2,
                backgroundImageRes = R.drawable.colab_2,
                brandTop = "BALENCIAGA",
                brandBottomRes = R.drawable.logo_puma
            ),
            CollabData(
                id = 3,
                backgroundImageRes = R.drawable.colab_1,
                brandTop = "ADIDAS",
                brandBottomRes = R.drawable.logo_puma
            )
        )
    }

    private fun loadPartners() {
        viewModelScope.launch {
            _partners.value = partnerRepository.getPartners()
        }
    }
    fun toggleLike(productId: Int) {
        viewModelScope.launch {
            val isLiked = likeRepository.toggleLike(productId)
            val currentProducts = _products.value
            val addLike = if (isLiked) 1 else -1
            val updatedProducts = currentProducts.map { product ->
                if (product.id == productId) {
                    product.copy(isLiked = isLiked, numLikes = product.numLikes + addLike)
                } else {
                    product
                }
            }
            _products.value = updatedProducts
        }
    }
    fun toggleDropNotification(dropId: Int) {
        val currentList = _drops.value
        _drops.value = currentList.map { drop ->
            if (drop.id == dropId) {
                drop.copy(isNotified = !drop.isNotified)
            } else {
                drop
            }
        }
    }

    fun reserveDrop(dropId: Int) {
        // Aquí iría la lógica para añadir a la cesta o reservar
        Log.d("ViewModel", "Reservando el drop con ID: $dropId")
    }

    private fun loadDrops() {
        // Calculamos el tiempo actual en milisegundos
        val now = System.currentTimeMillis()

        // Acaba dentro de 2 días, 14 horas, 32 min y 25 seg
        val futureTime1 =
            now + (2L * 24 * 60 * 60 * 1000) + (14L * 60 * 60 * 1000) + (32L * 60 * 1000) + (25L * 1000)

        _drops.value = listOf(
            DropData(
                id = 1,
                imageRes = R.drawable.colab_2, // Tu imagen real
                status = "• PRONTO DISPONIBLE",
                statusColor = Color(0xFFC7843B),
                title = "Regular jeans",
                collection = "STW Collection",
                targetTimestamp = futureTime1,
                isNotified = false
            ),
        )
    }

}


