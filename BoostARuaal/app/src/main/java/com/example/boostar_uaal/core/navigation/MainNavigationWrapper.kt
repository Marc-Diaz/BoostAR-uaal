package com.example.boostar_uaal.core.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.boostar_uaal.LocalAuthState
import com.example.boostar_uaal.core.utils.AuthState
import com.example.boostar_uaal.ui.screen.trendsScreen.TrendsScreen
import com.example.boostar_uaal.ui.screen.forYouScreen.ForYouScreen
import com.example.boostar_uaal.ui.screen.authScreen.AuthScreen
import com.example.boostar_uaal.ui.screen.feedScreen.FeedScreen
import com.example.boostar_uaal.ui.screen.homeScreen.HomeScreen
import com.example.boostar_uaal.ui.screen.loginScreen.LogInScreen
import com.example.boostar_uaal.ui.screen.onboardingChooseScreen.OnboardingChooseScreen
import com.example.boostar_uaal.ui.screen.onboardingChooseScreen.OnboardingChooseViewmodel
import com.example.boostar_uaal.ui.screen.onboardingTextScreen.OnboardingTextScreen
import com.example.boostar_uaal.ui.screen.singInScreen.SignInScreen
import com.example.boostar_uaal.ui.screen.arScreen.ArScreen
import com.example.boostar_uaal.ui.screen.basketScreen.BasketScreen
import com.example.boostar_uaal.ui.screen.challengeScreen.ChallengePointScreen
import com.example.boostar_uaal.ui.screen.challengeScreen.ChallengeScreen
import com.example.boostar_uaal.ui.screen.challengeScreen.ChallengeTextScreen
import com.example.boostar_uaal.ui.screen.challengeScreen.ChallengeTriviaScreen
import com.example.boostar_uaal.ui.screen.eventScreen.EventScreen
import com.example.boostar_uaal.ui.screen.gameScreen.GameScreen
import com.example.boostar_uaal.ui.screen.profileScreen.ProfileScreen

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun MainNavigationWrapper() {
    val authState = LocalAuthState.current
    val startRoute: Routes = when (authState) {
        AuthState.Authenticated -> Routes.HomeScreen
        AuthState.Unauthenticated -> Routes.AuthScreen
        else -> Routes.AuthScreen
    }
    val backStack = rememberNavBackStack(startRoute)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.back() },
        entryProvider = entryProvider {
            entry<Routes.AuthScreen> {
                AuthScreen(
                    navigateTo = { route, inclusive -> backStack.navigateTo(screen = route, inclusive = inclusive) },
                    back = { backStack.back() },
                    backTo = { }
                )
            }

            entry<Routes.LogInScreen> {
                LogInScreen(
                    navigateTo = { backStack.navigateTo(it, true) },
                    back = { backStack.back() },
                    backTo = { }
                )
            }

            entry<Routes.SignInScreen> {
                SignInScreen(
                    navigateTo = { backStack.navigateTo(it, inclusive = true) },
                    back = { backStack.back() },
                    backTo = { },
                )
            }
            entry<Routes.Authenticated> {
                LaunchedEffect(Unit) {
                    backStack.navigateTo(Routes.OnboardingTextScreen, inclusive = true)
                }
            }
            entry<Routes.OnboardingTextScreen> {
                OnboardingTextScreen(
                    navigateToChoose = {
                        backStack.navigateTo(Routes.OnboardingChooseScreen)
                    }
                )
            }
            entry<Routes.OnboardingChooseScreen> {
                OnboardingChooseScreen(
                    navigateTo = { backStack.navigateTo(it, inclusive = true) },
                    back = { backStack.back() },
                    backTo = { },
                    viewModel = OnboardingChooseViewmodel()
                )
            }

            entry<Routes.HomeScreen> {
                HomeScreen(
                    navigateTo = { backStack.navigateTo(it) }
                )
            }
            entry<Routes.FeedScreen> { b ->
                FeedScreen(
                    productId = b.productId,
                    navigateTo = { backStack.navigateTo(it)  },
                    back = { },
                    backTo = { },
                    sortOrder = b.sortOrder,
                    filters = b.filters
                )
            }

            entry<Routes.ForYouScreen> {
                ForYouScreen(
                    navigateTo = { backStack.navigateTo(it) },
                    back = { backStack.back() }
                )
            }

            entry<Routes.TrendsScreen> {
                TrendsScreen (
                    navigateTo = { backStack.navigateTo(it) }
                )
            }

            entry<Routes.ArScreen>{
                    b ->
                ArScreen(
                    back = { backStack.back() },
                    lensId = b.lensId,
                    product = b.product,
                    onPermissionDenied = { backStack.back() }
                )
            }
            entry<Routes.BasketScreen>{
                BasketScreen(
                    navigateTo = { backStack.navigateTo(it)  },
                    back = { },
                    backTo = { }
                )
            }

            entry<Routes.GameScreen>{
                GameScreen(
                    navigateTo = { backStack.navigateTo(it)  },
                    back = { },
                    backTo = { }
                )
            }

            entry<Routes.ProfileScreen>{
                ProfileScreen(
                    navigateTo = {
                        backStack.navigateTo(
                            screen = it,
                            inclusive = it == Routes.AuthScreen) },
                    back = { },
                    backTo = { }
                )
            }

            entry<Routes.ChallengeScreen>{ b ->
                ChallengeScreen(
                    challengeId = b.challengeId,
                    navigateTo = { backStack.navigateTo(Routes.ChallengeTriviaScreen) },
                    back = { },
                    backTo = { }
                )
            }

            entry<Routes.ChallengeTriviaScreen>{
                ChallengeTriviaScreen(
                    navigateTo = {
                        backStack.navigateTo(it)
                    }
                )
            }
            
            entry<Routes.ChallengeTextScreen>{
                ChallengeTextScreen(
                    navigateTo = { backStack.navigateTo(it) }
                )
            }

            entry<Routes.ChallengePointScreen>{
                ChallengePointScreen(
                    backTo = { backStack.navigateTo(it) }
                )
            }
            
            entry<Routes.EventScreen>{
                EventScreen(
                    navigateTo = { backStack.navigateTo(it)},
                    back = { backStack.back() },
                    backTo = {  }
                )
            }
        }
    )
}
