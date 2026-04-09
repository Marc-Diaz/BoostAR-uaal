package com.example.boostar_uaal.ui.screen.arScreen

import androidx.lifecycle.ViewModel
import com.example.boostar_uaal.core.entities.ProductDetail
import com.example.core.entities.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ArScreenViewModel(private val product: ProductDetail): ViewModel() {
    private var _facingFront: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val facingFront = _facingFront.asStateFlow()

    private var _showDialog: MutableStateFlow<Boolean> = MutableStateFlow<Boolean>(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    fun flipCamera(){
        _facingFront.value = !_facingFront.value
    }

    fun openDialog(){
        _showDialog.value = true
    }
    fun closeDialog(){
        _showDialog.value = false
    }
}