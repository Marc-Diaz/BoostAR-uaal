package com.example.boostar_uaal.ui.screen.forYouScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.core.components.AdaptiveFeedLayout
import com.example.boostar_uaal.core.components.BackButtonHeader
import com.example.boostar_uaal.core.components.BottomNavBar
import com.example.boostar_uaal.core.components.PartnerCarousel
import com.example.boostar_uaal.core.components.SectionHeader
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.data.models.SortOrder
import com.example.boostar_uaal.ui.screen.forYouScreen.components.CleanItemCarrousel
import com.example.boostar_uaal.ui.screen.homeScreen.components.HomeHero
import com.example.boostar_uaal.core.components.ProductCarrousel


@Composable
fun ForYouScreen(navigateTo: (Routes) -> Unit, back: () -> Unit) {

    val forYouScreenViewModel = viewModel<ForYouScreenViewModel>()
    val banners by forYouScreenViewModel.banners.collectAsState()
    val productsForYou by forYouScreenViewModel.productsForYou.collectAsState()
    val partners by forYouScreenViewModel.partners.collectAsState()

    LaunchedEffect(Unit) {
        forYouScreenViewModel.initializeParaTi()
    }

    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = {
            BottomNavBar(
                naviagetTo = { navigateTo(it) },
                currentRoute = Routes.ForYouScreen
            )
        },
        content = { paddingValues ->

            AdaptiveFeedLayout {

                BackButtonHeader(
                    Modifier.padding(top = paddingValues.calculateTopPadding()),
                    title = "Para Ti",
                    onBackClick = { back() }
                )
                HomeHero(
                    banners = banners,
                    showlikeBotton = false,
                    onTryArClick = { navigateTo(Routes.ArScreen(lensId = "")) }
                )
                SectionHeader(
                    title = "Tus reservas",
                    onClick = {  },
                    enabled = false
                )
                CleanItemCarrousel(
                    products = productsForYou,
                    onItemClick = { productId ->
                        navigateTo(
                            Routes.FeedScreen(productId, SortOrder.FORYOU)
                        )
                    }
                )
                SectionHeader(
                    title = "Tu estilo ",
                    onClick = { },
                    enabled = false
                )
                ProductCarrousel(
                    products = productsForYou,
                    onItemClick = { productId ->
                        navigateTo(
                            Routes.FeedScreen(productId, SortOrder.FORYOU)
                        )
                    },
                    onLikeClick = { forYouScreenViewModel.toggleLike(it) }
                )
                SectionHeader(
                    title = "Tus Outfits",
                    onClick = { },
                    enabled = false

                )

                SectionHeader(
                    title = "Tus Partners",
                    onClick = { },
                    enabled = false
                )
                PartnerCarousel(
                    partners = partners,
                    onItemClick = { }
                )



            }
        }
    )

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ParaTiScreenPreview() {
    ForYouScreen(
        navigateTo = { },
        back = { }
    )
}