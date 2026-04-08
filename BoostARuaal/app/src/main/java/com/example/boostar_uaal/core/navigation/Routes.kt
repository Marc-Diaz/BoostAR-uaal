package com.example.boostar_uaal.core.navigation

import androidx.navigation3.runtime.NavKey
import com.example.boostar_uaal.core.entities.Partner
import com.example.boostar_uaal.core.entities.ProductDetail
import com.example.boostar_uaal.data.models.SortOrder
import com.example.core.entities.Product
import kotlinx.serialization.Serializable
sealed class    Routes: NavKey {
    @Serializable
    data object AuthScreen: Routes()

    @Serializable
    data object BasketScreen: Routes()

    @Serializable
    data class FeedScreen(val productId: Int? = null, val sortOrder: String = SortOrder.FORYOU, val filters: List<String> = emptyList()): Routes()

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

    @Serializable
    data object GameScreen: Routes()
    @Serializable
    data object ProfileScreen: Routes()
    @Serializable
    data class ArScreen(val lensId: String, val product: ProductDetail): Routes()

    @Serializable
    data object ParatiScreen: Routes()

    @Serializable
    data object NovedadesScreen: Routes()

    @Serializable
    data object TendenciasScreen: Routes()



}