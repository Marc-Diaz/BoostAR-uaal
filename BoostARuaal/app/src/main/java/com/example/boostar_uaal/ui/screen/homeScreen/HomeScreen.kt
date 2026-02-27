package com.example.boostar_uaal.ui.screen.homeScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.boostar_uaal.ui.screen.homeScreen.components.ItemCarrousel
import com.example.boostar_uaal.core.navigation.Routes
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.core.components.AdaptiveFeedLayout
import com.example.boostar_uaal.core.components.SectionHeader
import com.example.boostar_uaal.ui.screen.homeScreen.components.HeroBannerData
import com.example.boostar_uaal.core.components.HomeBannerEstatic
import com.example.boostar_uaal.ui.screen.homeScreen.components.HomeHeader
import com.example.boostar_uaal.ui.screen.homeScreen.components.HomeHero
import com.example.boostar_uaal.ui.screen.homeScreen.components.HomeNav
import com.example.boostar_uaal.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(navigateTo: (Routes) -> Unit) {

    val homeScreenViewModel = viewModel<HomeScreenViewModel>()
    val products by homeScreenViewModel.products.collectAsState()
    val banners by homeScreenViewModel.banners.collectAsState()

    // nueva Carcasa Universal
    AdaptiveFeedLayout {

        HomeHeader()

        HomeNav(
            onItemClick = { route -> navigateTo(route) }
        )

        HomeHero(
            banners = banners,
            onTryArClick = { homeScreenViewModel.onTryArClick() }
        )

        ItemCarrousel(
            title = "Para ti >",
            products = products,
            onHeaderClick = { navigateTo(Routes.HomeScreen) },
            onItemClick = { productId -> navigateTo(Routes.FeedScreen(productId)) },
            onLikeClick = { }
        )

        ItemCarrousel(
            title = "Tendencias >",
            products = products,
            onHeaderClick = { navigateTo(Routes.HomeScreen) },
            onItemClick = { productId -> navigateTo(Routes.FeedScreen(productId)) },
            onLikeClick = { }
        )
        SectionHeader(title = "Febrero.",
            textColor = Color(0xFF0080FF), // pide el color azul Boost
            fontSize = 27.48.sp
        )

        HomeBannerEstatic(
            banner = HeroBannerData(
                imageRes = R.drawable.home_novedades_mes,
                label = "NOVEDADES",
                title = "El talento que está\ncambiando la moda.",
                subtitle = "Disponible ya en BoostAR.",
            ),
            onButtonClick = { }
        )

        SectionHeader(title = "Colaboraciones >",
            textColor = Color.Black,
            fontSize = 27.48.sp
        )





    }
}