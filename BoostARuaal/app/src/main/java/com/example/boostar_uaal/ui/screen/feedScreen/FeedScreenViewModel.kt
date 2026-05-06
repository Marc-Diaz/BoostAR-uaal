package com.example.boostar_uaal.ui.screen.feedScreen


import android.content.Context
import android.content.Intent
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.core.entities.ProductDetail
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.boostar_uaal.BoostArApplication.Companion.productRepository
import com.example.boostar_uaal.BoostArApplication.Companion.likeRepository
import com.example.boostar_uaal.BoostArApplication.Companion.cartRepository
import com.example.boostar_uaal.data.models.ProductFilters
/**
 * ViewModel que gestiona la lógica de presentación, la paginación bidireccional
 * y el estado interactivo de la pantalla de exploración principal (Feed estilo scroll infinito).
 *
 * @param sortOrder Criterio de ordenación inicial (ej. "novedades", "para ti").
 * @param productFilters Filtros activos a aplicar sobre las peticiones del feed.
 */

class FeedScreenViewModel(private val sortOrder: String, private val productFilters: ProductFilters) : ViewModel() {
    private val _products = MutableStateFlow<List<ProductDetail>>(emptyList())
    val products: StateFlow<List<ProductDetail>> = _products.asStateFlow()

    private var _lastSelectedIndex = MutableStateFlow<Int>(0)
    val lastSelectedIndex: StateFlow<Int> = _lastSelectedIndex

    private var _showDialog: MutableStateFlow<Boolean> = MutableStateFlow<Boolean>(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    private var _currentProduct: MutableStateFlow<ProductDetail?> = MutableStateFlow<ProductDetail?>(null)
    val currentProduct: StateFlow<ProductDetail?> = _currentProduct.asStateFlow()

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

    /**
     * Carga el estado inicial del feed. Si se proporciona un [initialProductId],
     * recupera ese producto primero para mostrarlo inmediatamente, y luego carga
     * silenciosamente las páginas adyacentes (anterior y siguiente) para permitir
     * el scroll en ambas direcciones sin interrupciones.
     */
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
    /**
     * Paginación hacia adelante (Scroll down).
     * Utiliza el ID del último elemento de la lista actual como cursor para
     * solicitar el siguiente bloque de productos a la base de datos y los añade al final.
     */
    fun loadNextPage() {
        viewModelScope.launch {
            try {
                val lastId = _products.value.lastOrNull()?.id
                val newItems = productRepository.getFeedProducts(
                    sortMode = sortOrder,
                    filters = productFilters,
                    refId = lastId,
                    direction = "next"
                )
                _products.value = (_products.value + newItems).distinctBy { it.id }
            } catch (e: Exception) {
            }
        }
    }
    /**
     * Paginación hacia atrás (Scroll up).
     * Utiliza el ID del primer elemento de la lista actual como cursor para
     * solicitar el bloque anterior de productos y los añade al inicio de la lista.
     */
    fun loadPrevPage() {
        viewModelScope.launch {
            try {
                val firstId = _products.value.firstOrNull()?.id ?: return@launch
                val newItems = productRepository.getFeedProducts(
                    sortMode = sortOrder,
                    filters = productFilters,
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
    /**
     * Actualiza el índice del producto que el usuario está visualizando actualmente
     * en el carrusel o feed vertical.
     *
     * @param index Posición actual en la lista.
     */
    fun saveCurrentIndex(index: Int){
        _lastSelectedIndex.value = index
    }

    fun openDialog(){
        _showDialog.value = true
    }
    fun closeDialog(){
        _showDialog.value = false
    }
    /**
     * Abre el menú nativo del sistema (Intent) para compartir el nombre del producto
     * actual que se está visualizando con otras aplicaciones o contactos.
     *
     * @param context Contexto de Android necesario para lanzar la actividad.
     */
    fun shareProduct(context: Context){
        val currentProduct = _products.value.get(_lastSelectedIndex.value)
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TEXT, currentProduct.name)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
       context.startActivity(shareIntent)
    }

    fun getCurrentProduct(){
        try {
            _currentProduct.value = _products.value[_lastSelectedIndex.value]
        }
        catch (e: Exception){

        }
    }
}