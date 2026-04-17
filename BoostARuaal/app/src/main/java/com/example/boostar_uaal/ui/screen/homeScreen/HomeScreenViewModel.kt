package com.example.boostar_uaal.ui.screen.homeScreen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.entities.PartnerData
import com.example.boostar_uaal.data.models.ProductFilters
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
import com.example.boostar_uaal.core.entities.Event
import com.example.boostar_uaal.core.entities.Multimedia
import com.example.boostar_uaal.core.entities.TypeMultimedia
import kotlinx.coroutines.Dispatchers

class HomeScreenViewModel : ViewModel() {

    private val _productsForYou = MutableStateFlow<List<Product>>(emptyList())
    val productsForYou: StateFlow<List<Product>> = _productsForYou.asStateFlow()
    private val _productsTrends = MutableStateFlow<List<Product>>(emptyList())
    val productsTrends: StateFlow<List<Product>> = _productsTrends.asStateFlow()

    private val _productsDiscounts = MutableStateFlow<List<Product>>(emptyList())
    val productsDiscounts: StateFlow<List<Product>> = _productsDiscounts.asStateFlow()
    private val _banners = MutableStateFlow<List<HeroBannerData>>(emptyList())
    val banners: StateFlow<List<HeroBannerData>> = _banners.asStateFlow()
    private val _licenses = MutableStateFlow<List<CollabData>>(emptyList())
    val collabs: StateFlow<List<CollabData>> = _licenses.asStateFlow()
    private val _partners = MutableStateFlow<List<PartnerData>>(emptyList())
    val partners: StateFlow<List<PartnerData>> = _partners.asStateFlow()

    private val _event: MutableStateFlow<Event?> = MutableStateFlow(null)
    val event: StateFlow<Event?> = _event.asStateFlow()

    init {
        initializeHome()
    }

    fun initializeHome(){
        viewModelScope.launch {
            launch(Dispatchers.IO) { loadBanners() }
            launch(Dispatchers.IO) { loadProductsForYou() }
            launch(Dispatchers.IO) { loadProductsTrends() }
            launch(Dispatchers.IO) { loadLicenses() }
            launch(Dispatchers.IO) { loadPartners() }
            launch(Dispatchers.IO) { loadEvent() }
            launch(Dispatchers.IO) { loadProductsDiscounts() }
        }
    }

    fun loadProductsForYou(){
        viewModelScope.launch(Dispatchers.IO) {
            _productsForYou.value = productRepository.getProducts(
                sortMode = SortOrder.FORYOU
            )
        }
    }

    fun loadProductsTrends(){
        viewModelScope.launch(Dispatchers.IO) {
            _productsTrends.value = productRepository.getProducts(
                sortMode = SortOrder.TRENDS
            )
        }
    }

    fun loadProductsDiscounts(){
        viewModelScope.launch(Dispatchers.IO) {
            _productsDiscounts.value = productRepository.getProducts(
                sortMode = SortOrder.DISCOUNT,
                filters = ProductFilters(discount = true)
            )
        }
    }

    fun loadBanners() {
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
    fun loadLicenses() {
        _licenses.value = listOf(
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

    fun loadEvent(){
        val event = Event(
                id = 1,
                isMain = true,
                title = "Bad Bunny<span style=\"color:#007AFF\">.</span>",
                bannerName = "¡Bad Bunny!",
                bannerDescription = "Visualiza los eventos más rompedores del momento",
                logo = "https://moygfqmmtuwvpeatrvhw.supabase.co/storage/v1/object/sign/imagenes/logo_bad_bunny.png?token=eyJraWQiOiJzdG9yYWdlLXVybC1zaWduaW5nLWtleV9hMWNkOTIxYi1kMGNiLTQyN2ItOTFlMC1lZTI2OGZlOGNmZWIiLCJhbGciOiJIUzI1NiJ9.eyJ1cmwiOiJpbWFnZW5lcy9sb2dvX2JhZF9idW5ueS5wbmciLCJpYXQiOjE3NzYxNTEwODQsImV4cCI6MTgwNzY4NzA4NH0.cni6XOVznVQSthyNKnB0cNEPuiwZwuKCeJH1Ll7y1JQ",
                bannerMedia = Multimedia(
                    id = 11,
                    multimediaURL = "https://moygfqmmtuwvpeatrvhw.supabase.co/storage/v1/object/sign/videos/banner_evento_bad_bunny.mp4?token=eyJraWQiOiJzdG9yYWdlLXVybC1zaWduaW5nLWtleV9hMWNkOTIxYi1kMGNiLTQyN2ItOTFlMC1lZTI2OGZlOGNmZWIiLCJhbGciOiJIUzI1NiJ9.eyJ1cmwiOiJ2aWRlb3MvYmFubmVyX2V2ZW50b19iYWRfYnVubnkubXA0IiwiaWF0IjoxNzc2MTU0NTQ3LCJleHAiOjE4MDc2OTA1NDd9.xW-KfdFq6jglYh390GK00dSeT5fQ5CuolDFbt0-7SjI",
                    type = TypeMultimedia.VIDEO
                ),
                productImage = "https://moygfqmmtuwvpeatrvhw.supabase.co/storage/v1/object/sign/imagenes/producto_evento_bad_bunny.png?token=eyJraWQiOiJzdG9yYWdlLXVybC1zaWduaW5nLWtleV9hMWNkOTIxYi1kMGNiLTQyN2ItOTFlMC1lZTI2OGZlOGNmZWIiLCJhbGciOiJIUzI1NiJ9.eyJ1cmwiOiJpbWFnZW5lcy9wcm9kdWN0b19ldmVudG9fYmFkX2J1bm55LnBuZyIsImlhdCI6MTc3NjE1MjU4OSwiZXhwIjoxODA3Njg4NTg5fQ.DYxLY36vw74BMXiTL1ZmgPzimL0PVIYD8eGimYLOxC0",
                productDescription = "Esta camiseta representa un momento icónico donde la música, la cultura y el espectáculo se unen en un escenario global.<br><br>Un símbolo de impacto que trasciende el evento y conecta a millones de personas.",
                model = "",
                isProductImageLeft = true
            )
        _event.value = event
    }
    fun loadPartners() {
        viewModelScope.launch(Dispatchers.IO) {
            _partners.value = partnerRepository.getPartners()
        }
    }
    fun toggleLike(productId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            likeRepository.toggleLike(productId)
        }
    }

    fun refreshLikes(){
        viewModelScope.launch(Dispatchers.Default) {
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
}


