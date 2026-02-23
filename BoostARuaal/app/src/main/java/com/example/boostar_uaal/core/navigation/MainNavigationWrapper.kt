package com.example.boostar_uaal.core.navigation


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
import com.example.boostar_uaal.ui.screen.onboardingTextScreen.OnboardingTextScreen
import com.example.boostar_uaal.ui.screen.singInScreen.SignInScreen

@Composable
fun MainNavigationWrapper(){
    val backStack = rememberNavBackStack(Routes.AuthScreen)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.back() },
        entryProvider = entryProvider {
            entry<Routes.AuthScreen> {
                AuthScreen(
                    navigateTo = { backStack.navigateTo(it)},
                    back = { backStack.back() },
                    backTo = {  }
                )
            }

            entry<Routes.LogInScreen> {
                LogInScreen(
                    navigateTo = { backStack.navigateTo(it)},
                    back = { backStack.back() },
                    backTo = {  }
                )
            }

            entry<Routes.SignInScreen>{
                SignInScreen(
                    navigateTo = { backStack.navigateTo(it)},
                    back = { backStack.back() },
                    backTo = {  }
                )
            }
            entry<Routes.Authenticated> {
                backStack.navigateTo(Routes.HomeScreen)

            }
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
            entry<Routes.OnboardingTextScreen> { backStack ->
                OnboardingTextScreen(
                    navigateTo = { },
                    back = {},
                    backTo = {}
                )
            }
            entry<Routes.OnboardingChooseScreen> { backStack ->
                OnboardingTextScreen(
                    navigateTo = { },
                    back = {},
                    backTo = {}
                )
            }
        }
    )
}
