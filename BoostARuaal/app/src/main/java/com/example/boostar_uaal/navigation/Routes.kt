package com.example.boostar_uaal.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
sealed class Routes: NavKey {
    @Serializable
    data object AuthScreen: Routes()

    @Serializable
    data object BasketScreen: Routes()

    @Serializable
    data class FeedScreen(val productId: Int): Routes()

    @Serializable
    data object HomeScreen: Routes()

    @Serializable
    data object LogInScreen: Routes()

    @Serializable
    data object OnboardingChooseScreen: Routes()

    @Serializable
    data object OnboardingTextScreen: Routes()

    @Serializable
    data object SignInScreen: Routes()

    @Serializable
    data object Authenticated: Routes()
}