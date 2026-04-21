package com.example.boostar_uaal.core.navigation

import android.util.Log
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class NavViewModel: ViewModel(){
    private var _feedUuid: MutableStateFlow<String?> = MutableStateFlow<String?>(null)
    val feedUuid: StateFlow<String?> = _feedUuid.asStateFlow()

    fun setUuid(newUuid: String?){
        _feedUuid.value = newUuid
    }

    fun clearUuid(){
        _feedUuid.value = null
    }
}