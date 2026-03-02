package com.example.boostar_uaal.ui.screen.authScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.BoostArApplication
import com.example.boostar_uaal.core.utils.AuthState
import io.github.jan.supabase.auth.user.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthScreenViewModel: ViewModel() {
    val authRepository = BoostArApplication.authRepository
    private val _session = MutableStateFlow<UserSession?>(null)
    private val _authState = MutableStateFlow<AuthState>(AuthState.Loading)
    val authState = _authState.asStateFlow()

    init {
        checkExistingSession()
    }
    fun checkExistingSession(){
        viewModelScope.launch {
            _session.value = authRepository.loadSession()
            if(_session.value != null){
                _authState.value = AuthState.Authenticated
            }
            else{
                _authState.value = AuthState.Unauthenticated
            }
        }

    }
}