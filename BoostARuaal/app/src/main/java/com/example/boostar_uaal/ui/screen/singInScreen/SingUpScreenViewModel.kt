package com.example.boostar_uaal.ui.screen.singInScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.BoostArApplication
import com.example.boostar_uaal.core.navigation.Routes
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SingUpScreenViewModel(): ViewModel() {

    private val authRepository = BoostArApplication.authRepository
    private val userRepository = BoostArApplication.userRepository
    private val _isCompanyAccount = MutableStateFlow(false)
    val isCompanyAccount: StateFlow<Boolean> = _isCompanyAccount.asStateFlow()

    private var _errorMessage: MutableStateFlow<String?> = MutableStateFlow(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun toggleCompanyAccount() {
        _isCompanyAccount.value = !_isCompanyAccount.value
    }

    fun handleGoogleSignInResult(result: NativeSignInResult, navigateTo: (Routes) -> Unit) {
        when(result) {
            is NativeSignInResult.Success -> {
                viewModelScope.launch {
                    Log.d("AuthRole", "${userRepository.hasUserRole()}")
                    if (userRepository.hasUserRole()){
                        authRepository.clearSession()
                        _errorMessage.value = "Ya tienes un usuario creado.."
                    }
                    else {
                        userRepository.setUserRole(isCompanyAccount.value)
                        authRepository.saveSession()
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
    fun clearError(){
        _errorMessage.value = null
    }
}