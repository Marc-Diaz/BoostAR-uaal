package com.example.boostar_uaal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.boostar_uaal.core.utils.back
import com.example.boostar_uaal.core.utils.navigateTo
import com.example.boostar_uaal.ui.screen.authScreen.AuthScreen
import com.example.boostar_uaal.ui.screen.feedScreen.FeedScreen
import com.example.boostar_uaal.ui.screen.homeScreen.HomeScreen
import com.example.boostar_uaal.ui.screen.loginScreen.LogInScreen
import com.example.boostar_uaal.ui.screen.singInScreen.SignInScreen

@Composable
fun InternalNavigationWrapper(){
    val backStack = rememberNavBackStack(Routes.HomeScreen)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.back() },
        entryProvider = entryProvider {
            entry<Routes.HomeScreen> {
                HomeScreen(
                    navigateTo = { backStack.navigateTo(it) }
                )
            }
            entry<Routes.FeedScreen> { backStack ->
                FeedScreen(
                    backStack.productId,
                    navigateTo = { },
                    back = { },
                    backTo = { }
                )
            }
        }
    )
}