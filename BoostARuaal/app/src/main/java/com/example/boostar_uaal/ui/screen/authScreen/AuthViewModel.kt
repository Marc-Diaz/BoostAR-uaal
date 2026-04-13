package com.example.boostar_uaal.ui.screen.authScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.core.utils.AuthState
import io.github.jan.supabase.auth.user.UserSession
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.boostar_uaal.BoostArApplication.Companion.authRepository
import com.example.boostar_uaal.BoostArApplication.Companion.userRepository
import io.github.jan.supabase.auth.Auth

class AuthViewModel: ViewModel() {
    private val _session = MutableStateFlow<UserSession?>(null)
    private val _authState = MutableStateFlow<AuthState>(AuthState.Loading)
    val authState = _authState.asStateFlow()
    private val _isCompanyAccount = MutableStateFlow(false)
    val isCompanyAccount: StateFlow<Boolean> = _isCompanyAccount.asStateFlow()

    private var _errorMessage: MutableStateFlow<String?> = MutableStateFlow(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    init {
        checkExistingSession()
    }
    fun checkExistingSession(){
        viewModelScope.launch {
            _session.value = authRepository.loadSession()
            if(_session.value == null) _authState.value = AuthState.Unauthenticated
            else if (!authRepository.isAccessTokenValid()) _authState.value = AuthState.Unauthenticated
            else{
                authRepository.refreshSession()
                _authState.value = AuthState.Authenticated
            }
        }

    }
    fun toggleCompanyAccount() {
        _isCompanyAccount.value = !_isCompanyAccount.value
    }

    fun handleGoogleLogIn(
        result: NativeSignInResult,
        navigateTo: (Routes) -> Unit
    ) {
        when (result) {
            is NativeSignInResult.Success -> {

                viewModelScope.launch {
                    if (!userRepository.hasUserRole()){
                        authRepository.clearSession()
                        _errorMessage.value = "No tienes un rol asignado."
                        return@launch
                    }
                    authRepository.saveSession()
                    _authState.value = AuthState.Authenticated
                    navigateTo(Routes.HomeScreen)
                }

            }
            is NativeSignInResult.Error -> {
                navigateTo(Routes.AuthScreen)
            }
            is NativeSignInResult.ClosedByUser -> {

            }
            is NativeSignInResult.NetworkError -> {
            }
        }
    }

    fun handleGoogleSignInResult(result: NativeSignInResult, navigateTo: (Routes) -> Unit) {
        when(result) {
            is NativeSignInResult.Success -> {
                viewModelScope.launch {
                    if (userRepository.hasUserRole()){
                        authRepository.clearSession()
                    }
                    else {
                        userRepository.setUserRole(isCompanyAccount.value)
                        authRepository.saveSession()
                        _authState.value = AuthState.Authenticated
                        navigateTo(Routes.Authenticated)
                    }
                }

            }
            is NativeSignInResult.Error -> {
                navigateTo(Routes.AuthScreen)
            }
            else -> { }
        }
    }
    fun signOut(onSuccess: () -> Unit){
        viewModelScope.launch{
            authRepository.signOut()
            _authState.value = AuthState.Unauthenticated
            onSuccess()
        }
    }
    fun clearError(){
        _errorMessage.value = null
    }
}