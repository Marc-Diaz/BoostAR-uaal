package com.example.boostar_uaal.ui.screen.arScreen

import androidx.lifecycle.ViewModel
import com.example.boostar_uaal.core.entities.ProductDetail
import com.example.core.entities.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ArScreenViewModel(): ViewModel() {
    private var _facingFront: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val facingFront = _facingFront.asStateFlow()

    fun flipCamera(){
        _facingFront.value = !_facingFront.value
    }

}