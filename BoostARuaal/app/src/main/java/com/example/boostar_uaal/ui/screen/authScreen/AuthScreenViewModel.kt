package com.example.boostar_uaal.ui.screen.authScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.BoostArApplication
import io.github.jan.supabase.auth.user.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthScreenViewModel: ViewModel() {
    val authRepository = BoostArApplication.authRepository
    private val _session = MutableStateFlow<UserSession?>(null)
    private val _authenticated = MutableStateFlow<Boolean>(false)
    val authenticated = _authenticated.asStateFlow()

    init {
        checkExistingSession()
    }
    fun checkExistingSession(){
        viewModelScope.launch {
            _session.value = authRepository.loadSession()

        }
        Log.d("AuthScreenViewModel", "${_session.value != null}")
        _authenticated.value = _session.value != null
    }

}