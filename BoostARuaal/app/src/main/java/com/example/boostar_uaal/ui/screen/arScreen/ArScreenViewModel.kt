package com.example.boostar_uaal.ui.screen.arScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ArScreenViewModel: ViewModel() {
    private var _facingFront: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val facingFront = _facingFront.asStateFlow()

    fun toggleCamera(){
        _facingFront.value = !_facingFront.value
    }
}