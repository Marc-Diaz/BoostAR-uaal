package com.example.boostar_uaal.ui.screen.trendsScreen

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.BoostArApplication.Companion.likeRepository
import com.example.boostar_uaal.BoostArApplication.Companion.partnerRepository
import com.example.boostar_uaal.BoostArApplication.Companion.productRepository
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.entities.LikeStyle
import com.example.boostar_uaal.core.entities.PartnerData
import com.example.boostar_uaal.data.models.SortOrder
import com.example.boostar_uaal.ui.screen.homeScreen.components.CollabData
import com.example.boostar_uaal.ui.screen.homeScreen.components.HeroBannerData
import com.example.core.entities.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TrendsScreenViewModel: ViewModel() {
    private val _banners = MutableStateFlow<List<HeroBannerData>>(emptyList())
    val banners: StateFlow<List<HeroBannerData>> = _banners.asStateFlow()

    private val _productsForYou = MutableStateFlow<List<Product>>(emptyList())
    val productsForYou: StateFlow<List<Product>> = _productsForYou.asStateFlow()
    private val _productsTrends = MutableStateFlow<List<Product>>(emptyList())
    val productsTrends: StateFlow<List<Product>> = _productsTrends.asStateFlow()
    private val _partners = MutableStateFlow<List<PartnerData>>(emptyList())
    val partners: StateFlow<List<PartnerData>> = _partners.asStateFlow()
    private val _collabs = MutableStateFlow<List<CollabData>>(emptyList())
    val collabs: StateFlow<List<CollabData>> = _collabs.asStateFlow()

    private val _barGraph: MutableStateFlow<List<LikeStyle>>  = MutableStateFlow(emptyList())
    fun initializeTrends(){
        loadBanners()
        loadProductsForYou()
        loadPartners()
        loadProductsTrends()
        loadCollabs()
        loadBarGraph()
        refreshLikes()
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

            }
        }
    }

    private fun loadPartners() {
        viewModelScope.launch {
            _partners.value = partnerRepository.getPartners()
        }
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

    fun loadBarGraph(){
        viewModelScope.launch {
            _barGraph.value = likeRepository.getTotalLikesByStyle()
        }
    }

}