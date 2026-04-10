package com.example.boostar_uaal.ui.screen.homeScreen

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.entities.DropData
import com.example.boostar_uaal.core.entities.PartnerData
import com.example.boostar_uaal.data.models.ProductFilter
import com.example.boostar_uaal.data.models.SortOrder
import com.example.boostar_uaal.ui.screen.homeScreen.components.CollabData
import com.example.boostar_uaal.ui.screen.homeScreen.components.HeroBannerData
import com.example.core.entities.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.boostar_uaal.BoostArApplication.Companion.productRepository
import com.example.boostar_uaal.BoostArApplication.Companion.likeRepository
import com.example.boostar_uaal.BoostArApplication.Companion.partnerRepository

class HomeScreenViewModel : ViewModel() {

    private val _productsForYou = MutableStateFlow<List<Product>>(emptyList())
    val productsForYou: StateFlow<List<Product>> = _productsForYou.asStateFlow()
    private val _productsTrends = MutableStateFlow<List<Product>>(emptyList())
    val productsTrends: StateFlow<List<Product>> = _productsTrends.asStateFlow()

    private val _productsDiscounts = MutableStateFlow<List<Product>>(emptyList())
    val productsDiscounts: StateFlow<List<Product>> = _productsDiscounts.asStateFlow()
    private val _banners = MutableStateFlow<List<HeroBannerData>>(emptyList())
    val banners: StateFlow<List<HeroBannerData>> = _banners.asStateFlow()
    private val _collabs = MutableStateFlow<List<CollabData>>(emptyList())
    val collabs: StateFlow<List<CollabData>> = _collabs.asStateFlow()
    private val _partners = MutableStateFlow<List<PartnerData>>(emptyList())
    val partners: StateFlow<List<PartnerData>> = _partners.asStateFlow()
    private val _drops = MutableStateFlow<List<DropData>>(emptyList())
    val drops: StateFlow<List<DropData>> = _drops.asStateFlow()

    fun initializeHome(){
        loadProducts()
        loadBanners()
        loadCollabs()
        loadPartners()
        loadDrops()
        refreshLikes()
    }
    fun loadProducts() {
        viewModelScope.launch {
            loadProductsForYou()
            loadProductsTrends()
            loadProductsDiscounts()
        }
    }

    private fun loadProductsForYou(){
        viewModelScope.launch {
            _productsForYou.value = productRepository.getProducts(
                sortMode = SortOrder.FORYOU
            )
        }
    }

    private fun loadProductsTrends(){
        viewModelScope.launch {
            _productsTrends.value = productRepository.getProducts(
                sortMode = SortOrder.TRENDS
            )
        }
    }

    private fun loadProductsDiscounts(){
        viewModelScope.launch {
            _productsDiscounts.value = productRepository.getProducts(
                sortMode = SortOrder.DISCOUNT,
                filters = listOf(ProductFilter.DISCOUNT)
            )
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
    private fun loadCollabs() {
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
                _productsForYou.value = applyLikes(_productsForYou.value)
                _productsTrends.value = applyLikes(_productsTrends.value)
                _productsDiscounts.value = applyLikes(_productsDiscounts.value)
            }
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
        Log.d("ViewModel", "Reservando el drop con ID: $dropId")
    }

    private fun loadDrops() {
        val now = System.currentTimeMillis()
        val futureTime1 = now + (2L * 24 * 60 * 60 * 1000) + (14L * 60 * 60 * 1000) + (32L * 60 * 1000) + (25L * 1000)

        _drops.value = listOf(
            DropData(
                id = 1,
                imageRes = R.drawable.colab_2,
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


