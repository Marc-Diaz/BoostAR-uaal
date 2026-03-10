package com.example.boostar_uaal.ui.screen.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.core.components.DropCard
import com.example.boostar_uaal.core.components.HomeBannerEventsEstatic
import com.example.boostar_uaal.core.components.PartnerCarousel
import com.example.boostar_uaal.ui.screen.homeScreen.components.CollabCarousel

@Composable
fun HomeScreen(navigateTo: (Routes) -> Unit) {

    val homeScreenViewModel = viewModel<HomeScreenViewModel>()
    val products by homeScreenViewModel.products.collectAsState()
    val banners by homeScreenViewModel.banners.collectAsState()
    val collabs by homeScreenViewModel.collabs.collectAsState()
    val partners by homeScreenViewModel.partners.collectAsState()
    val drops by homeScreenViewModel.drops.collectAsState()
    val context = LocalContext.current
    // nueva Carcasa Universal
    AdaptiveFeedLayout {

        HomeHeader()

        HomeNav(
            onItemClick = { route -> navigateTo(route) }
        )

        HomeHero(
            banners = banners,
            onTryArClick = { homeScreenViewModel.onTryArClick(context) }
        )
        SectionHeader(
            title = "Para ti >",
            onClick = { navigateTo(Routes.HomeScreen) }
        )

        ItemCarrousel(

            products = products,
            onItemClick = { productId -> navigateTo(Routes.FeedScreen(productId)) },
            onLikeClick = { homeScreenViewModel.toggleLike(it) }
        )

        SectionHeader(
            title = "Tendencias >",
            onClick = { navigateTo(Routes.HomeScreen) }
        )


        ItemCarrousel(
            products = products,
            onItemClick = { productId -> navigateTo(Routes.FeedScreen(productId)) },
            onLikeClick = { }
        )
        SectionHeader(
            title = "Febrero.",
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

        SectionHeader(
            title = "Colaboraciones >",
            textColor = Color.Black,
            fontSize = 27.48.sp
        )

        CollabCarousel(
            collabs = collabs,
            onItemClick = { collabId ->
                // Aquí en el futuro navega al detalle de la colaboración
                // navigateTo(Routes.CollabDetailScreen(collabId))
            }
        )

        SectionHeader(
            title = "Nuevos Partners >",
            onClick = { navigateTo(Routes.HomeScreen) }
        )

        PartnerCarousel(
            partners = partners,
            onItemClick = { partnerId ->
                // Acción al tocar el círculo de un partner
            }
        )

        SectionHeader(
            title = "Eventos >",
            onClick = { navigateTo(Routes.HomeScreen) }
        )

        HomeBannerEventsEstatic(
            banner = HeroBannerData(
                imageRes = R.drawable.titi,
                label = "",
                title = "Bad Bunny",
                subtitle = "Gana unas entradas para el nuevo concierto\n" +
                        "de Bad Bunny en España.",
            )
        )

        SectionHeader(
            title = "Próximante >",
            textColor = Color.Red,
            onClick = { navigateTo(Routes.HomeScreen) }
        )


            drops.forEach { dropData ->
                DropCard(
                    drop = dropData,
                    onReserveClick = { homeScreenViewModel.reserveDrop(dropData.id) },
                    onBellClick = { homeScreenViewModel.toggleDropNotification(dropData.id) }
                )
            }

        Spacer(modifier = Modifier.height(32.dp))

        SectionHeader(
            title = "Ofertas >",
            textColor = Color.Red,
            onClick = { navigateTo(Routes.HomeScreen) }
        )
        ItemCarrousel(
            products = products,
            onItemClick = { productId -> navigateTo(Routes.FeedScreen(productId)) },
            onLikeClick = { }
        )

    }
}