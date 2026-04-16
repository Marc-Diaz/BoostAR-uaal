package com.example.boostar_uaal.ui.screen.newPartnerScreens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.BoostArApplication.Companion.likeRepository
import com.example.boostar_uaal.BoostArApplication.Companion.partnerRepository
import com.example.boostar_uaal.BoostArApplication.Companion.productRepository
import com.example.boostar_uaal.core.entities.PartnerData
import com.example.boostar_uaal.data.models.ProductFilters
import com.example.boostar_uaal.data.models.SortOrder
import com.example.core.entities.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewPartnerScreenViewModel : ViewModel(){
    private var _partners: MutableStateFlow<List<PartnerData>> = MutableStateFlow(emptyList())
    val partners: StateFlow<List<PartnerData>> = _partners.asStateFlow()

    private var _products: MutableStateFlow<Map<String, List<Product>>> = MutableStateFlow(emptyMap())
    val products: StateFlow<Map<String, List<Product>>> = _products.asStateFlow()


    fun loadPartners(){
        viewModelScope.launch {
            _partners.value = partnerRepository.getPartners()
        }
    }
    fun loadProducts(partnerId: String){
        viewModelScope.launch {
            val products = productRepository.getProducts(
                sortMode = SortOrder.FORYOU,
                filters = ProductFilters(partnerId = partnerId),

            )
            _products.update { map ->
                val newMap = map.toMutableMap()
                newMap[partnerId] = products
                newMap
            }

        }
    }

    fun toggleLike(productId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            likeRepository.toggleLike(productId)
        }
    }

    fun refreshLikes() {
        viewModelScope.launch(Dispatchers.Default) {
            likeRepository.likeStateFlow.collect { likeMap ->
                if (likeMap.isEmpty()) return@collect

                _products.update { currentMap ->
                    currentMap.mapValues { (_, productList) ->
                        productList.map { p ->
                            val liked = likeMap[p.id] ?: return@map p
                            if (liked == p.isLiked) return@map p

                            p.copy(
                                isLiked = liked,
                                numLikes = p.numLikes + if (liked) 1L else -1L
                            )
                        }
                    }
                }
            }
        }
    }
}