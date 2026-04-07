package com.example.boostar_uaal.ui.screen.ParaTiScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.R
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.core.components.AdaptiveFeedLayout
import com.example.boostar_uaal.core.components.BackButtonHeader
import com.example.boostar_uaal.core.components.BottomNavBar
import com.example.boostar_uaal.core.components.PartnerCarousel
import com.example.boostar_uaal.core.components.SectionHeader
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.core.theme.secondaryTextColor
import com.example.boostar_uaal.data.models.SortOrder
import com.example.boostar_uaal.ui.screen.ParaTiScreen.components.CleanItemCarrousel
import com.example.boostar_uaal.ui.screen.ParaTiScreen.components.OnstyleChoosen
import com.example.boostar_uaal.ui.screen.homeScreen.components.HomeHero
import com.example.boostar_uaal.ui.screen.homeScreen.components.ItemCarrousel


@Composable
fun ParatiScreen(navigateTo: (Routes) -> Unit) {

    val paratiScreenViewModel = viewModel<ParatiScreenViewModel>()
    val banners by paratiScreenViewModel.banners.collectAsState()
    val productsForYou by paratiScreenViewModel.productsForYou.collectAsState()
    val partners by paratiScreenViewModel.partners.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        paratiScreenViewModel.initializeParaTi()
    }

    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = {
            BottomNavBar(
                naviagetTo = { navigateTo(it) },
                currentRoute = Routes.ParatiScreen
            )
        },
        content = { paddingValues ->

            AdaptiveFeedLayout() {

                BackButtonHeader(
                    Modifier.padding(top = paddingValues.calculateTopPadding()),
                    title = "Para Ti",
                    onBackClick = { navigateTo(Routes.HomeScreen) }
                )
                HomeHero(
                    banners = banners,
                    onLikeClick = {},
                    showlikeBotton = true,
                    onTryArClick = { paratiScreenViewModel.onTryArClick(context) }
                )
                SectionHeader(
                    title = "Tus reservas",
                    onClick = { navigateTo(Routes.ParatiScreen) }
                )
                CleanItemCarrousel(
                    products = productsForYou,
                    onItemClick = { productId ->
                        navigateTo(
                            Routes.FeedScreen(productId, SortOrder.FORYOU)
                        )
                    },

                    )
                SectionHeader(
                    title = "Tu estilo ",
                    onClick = { navigateTo(Routes.ParatiScreen) }
                )
                ItemCarrousel(
                    products = productsForYou,
                    onItemClick = { productId ->
                        navigateTo(
                            Routes.FeedScreen(productId, SortOrder.FORYOU)
                        )
                    },
                    onLikeClick = { paratiScreenViewModel.toggleLike(it) }
                )
                SectionHeader(
                    title = "Tus Outfits",
                    onClick = { navigateTo(Routes.ParatiScreen) }

                )//crea uno nuevo solo outfist
                /*OnboardingChooseItem(
                    product = product,
                    isSelected = isSelected,
                    clickable = { viewModel.toggleOption(product.id) },

                    )*/

                SectionHeader(
                    title = "Tus Partners",
                    onClick = {navigateTo(Routes.ParatiScreen)}
                )
                PartnerCarousel(
                    partners = partners,
                    onItemClick = {}
                )



            }
        }
    )

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ParaTiScreenPreview() {
    ParatiScreen(
        navigateTo = { /* Función vacía de prueba, el preview no navega */ }
    )
}