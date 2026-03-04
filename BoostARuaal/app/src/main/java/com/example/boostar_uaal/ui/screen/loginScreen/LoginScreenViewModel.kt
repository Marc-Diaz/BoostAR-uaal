package com.example.boostar_uaal.ui.screen.loginScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.BoostArApplication
import com.example.boostar_uaal.core.navigation.Routes
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import kotlinx.coroutines.launch

class LoginScreenViewModel: ViewModel() {

    val authRepository = BoostArApplication.authRepository
    fun handleGoogleSignInResult(
        result: NativeSignInResult,
        navigateTo: (Routes) -> Unit
    ) {
        when (result) {
            is NativeSignInResult.Success -> {

                viewModelScope.launch {
                    if (!authRepository.hasUserRole()) throw Exception("No tienes rol")
                    // authRepository.saveSession()
                }
                navigateTo(Routes.HomeScreen)
            }
            is NativeSignInResult.Error -> {
                navigateTo(Routes.AuthScreen)
            }
            is NativeSignInResult.ClosedByUser -> {

            }
            is NativeSignInResult.NetworkError -> {
            }
            else -> {
            }
        }
    }

}