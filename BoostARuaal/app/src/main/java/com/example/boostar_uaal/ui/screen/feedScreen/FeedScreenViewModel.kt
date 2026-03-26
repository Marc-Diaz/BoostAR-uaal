package com.example.boostar_uaal.ui.screen.feedScreen


import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.State
import androidx.core.content.ContextCompat.startActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.BoostArApplication
import com.example.boostar_uaal.core.entities.ProductDetail
import com.example.boostar_uaal.core.repository.CartRepository
import com.example.boostar_uaal.core.repository.LikeRepository
import com.example.boostar_uaal.core.repository.ProductRepository
import com.example.boostar_uaal.core.repository.UserRepository
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FeedScreenViewModel(private val sortOrder: String) : ViewModel() {

    private val productRepository: ProductRepository = BoostArApplication.productRepository
    private val likeRepository: LikeRepository = BoostArApplication.likeRepository
    private val cartRepository: CartRepository = BoostArApplication.cartRepository
    private val userRepository: UserRepository = BoostArApplication.userRepository

    private val _products = MutableStateFlow<List<ProductDetail>>(emptyList())
    val products: StateFlow<List<ProductDetail>> = _products.asStateFlow()

    private var _lastSelectedIndex = MutableStateFlow<Int>(0)
    val lastSelectedIndex: StateFlow<Int> = _lastSelectedIndex

    private var _showDialog: MutableStateFlow<Boolean> = MutableStateFlow<Boolean>(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    private var _isUserAuthenticated: MutableStateFlow<Boolean> = MutableStateFlow<Boolean>(false)
    val isUserAuthenticated: StateFlow<Boolean> = _isUserAuthenticated.asStateFlow()

    fun initializeFeed(initialProductId: Int? = null) {
        loadInitialProducts(initialProductId)
        refreshLikes()
    }

    fun refreshLikes(){
        viewModelScope.launch {
            likeRepository.likeStateFlow.collect { likeMap ->
                if (likeMap.isEmpty()) return@collect
                _products.value = _products.value.map { p ->
                    val liked = likeMap[p.id] ?: return@map p
                    if (liked == p.isLiked) return@map p
                    p.copy(isLiked = liked, numLikes = p.numLikes + if (liked) 1 else -1)
                }
            }
        }
    }

    fun loadInitialProducts(initialProductId: Int? = null){
        Log.d("BEFORE Products ViewModel", "${_products.value}")
        Log.d("Product Paramenters", "Sort: $sortOrder, refId: ${_products.value.lastOrNull()?.id}, direction: next")
        viewModelScope.launch {
            if (initialProductId != null) {
                val initialProduct = productRepository.getProductById(initialProductId)
                _products.value = listOf(initialProduct)
            }
            loadPrevPage()
            loadNextPage()
            Log.d("AFTER Products ViewModel", "${_products.value}")
        }
    }

    fun loadNextPage() {
        Log.d("Productos ViewModel swipe next", "LLEGA")
        viewModelScope.launch {
            try {
                val lastId = _products.value.lastOrNull()?.id
                val newItems = productRepository.getFeedProducts(
                    sortMode = sortOrder,
                    refId = lastId,
                    direction = "next"
                )
                _products.value = (_products.value + newItems).distinctBy { it.id }
            } catch (e: Exception) {
                Log.e("FeedScreenViewModel", "Error cargando siguientes: ${e.message}")
            }
        }
    }

    fun loadPrevPage() {

        viewModelScope.launch {
            try {
                val firstId = _products.value.firstOrNull()?.id ?: return@launch
                val newItems = productRepository.getFeedProducts(
                    sortMode = sortOrder,
                    refId = firstId,
                    direction = "prev"
                )
                Log.d("Productos ViewModel prev new", "$newItems")
                _products.value = (newItems + _products.value).distinctBy { it.id }
            } catch (e: Exception) {
                Log.e("FeedScreenViewModel", "Error cargando anteriores: ${e.message}")
            }
        }
    }

    fun toggleLike(productId: Int) {
        viewModelScope.launch {
            likeRepository.toggleLike(productId)
        }
    }

    fun addProductToCart(currentProduct: ProductDetail, colorIdx: Int? = null, sizeIdx: Int, quantity: Int = 1){
        viewModelScope.launch {
            val productId = currentProduct.id
            val colorId: Int? = if (colorIdx != null) currentProduct.colors[colorIdx].id else null
            val sizeId = currentProduct.sizes[sizeIdx].id
            Log.d("Producto Cart IDs", "$productId, $colorId, $sizeId")
            cartRepository.addProductToCart(productId, colorId, sizeId, quantity)
        }
    }

    fun saveCurrentIndex(index: Int){
        _lastSelectedIndex.value = index
    }

    fun openDialog(){
        _showDialog.value = true
    }
    fun closeDialog(){
        _showDialog.value = false
    }

    fun shareProduct(context: Context){
        val currentProduct = _products.value.get(_lastSelectedIndex.value)
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TEXT, currentProduct.name)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
       context.startActivity(shareIntent)
    }
}