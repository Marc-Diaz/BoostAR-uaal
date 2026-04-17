package com.example.boostar_uaal.ui.screen.homeScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.boostar_uaal.core.components.ProductCarrousel
import com.example.boostar_uaal.core.navigation.Routes
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.core.components.SectionHeader
import com.example.boostar_uaal.ui.screen.homeScreen.components.HeroBannerData
import com.example.boostar_uaal.core.components.HomeBannerStatic
import com.example.boostar_uaal.ui.screen.homeScreen.components.HomeHeader
import com.example.boostar_uaal.ui.screen.homeScreen.components.HomeHero
import com.example.boostar_uaal.ui.screen.homeScreen.components.HomeNav
import com.example.boostar_uaal.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.core.components.AdaptiveFeedLayout
import com.example.boostar_uaal.core.components.BottomNavBar
import com.example.boostar_uaal.core.components.EventBanner
import com.example.boostar_uaal.core.components.PartnerCarousel
import com.example.boostar_uaal.core.theme.primaryColor
import com.example.boostar_uaal.data.models.ProductFilters
import com.example.boostar_uaal.data.models.SortOrder

import com.example.boostar_uaal.ui.screen.homeScreen.components.LicensesCarousel


@Composable
fun HomeScreen(navigateTo: (Routes) -> Unit) {

    val homeScreenViewModel = viewModel<HomeScreenViewModel>()
    val productsForYou by homeScreenViewModel.productsForYou.collectAsState()
    val productsTrends by homeScreenViewModel.productsTrends.collectAsState()
    val productsDiscounts by homeScreenViewModel.productsDiscounts.collectAsState()
    val banners by homeScreenViewModel.banners.collectAsState()
    val collabs by homeScreenViewModel.collabs.collectAsState()
    val partners by homeScreenViewModel.partners.collectAsState()
    val event by homeScreenViewModel.event.collectAsState()

    LaunchedEffect(Unit) {
        homeScreenViewModel.refreshLikes()
    }
    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = {
            BottomNavBar(
                naviagetTo = { navigateTo(it) },
                currentRoute = Routes.HomeScreen
            )
        },
        content = { paddingValues ->
            AdaptiveFeedLayout {
                HomeHeader(
                    modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
                    navEnabled = false)
                HomeNav(
                    onItemClick = {  navigateTo(it) }
                )
                HomeHero(
                    banners = banners,
                    onTryArClick = { navigateTo(Routes.ArScreen(
                        lensId = "9e454816-6690-46a9-98ab-571f7f35ca8b"
                    )) }
                )
                SectionHeader(
                    title = "Para ti",
                    onClick = { navigateTo(Routes.ForYouScreen) }
                )

                ProductCarrousel(
                    products = productsForYou,
                    onItemClick = { productId -> navigateTo(
                        Routes.FeedScreen(productId, SortOrder.FORYOU)) },
                    onLikeClick = { homeScreenViewModel.toggleLike(it) }
                )

                SectionHeader(
                    title = "Tendencias",
                    onClick = { navigateTo(Routes.TrendsScreen) }
                )


                ProductCarrousel(
                    products = productsTrends,
                    onItemClick = { productId -> navigateTo(Routes.FeedScreen(productId, SortOrder.TRENDS)) },
                    onLikeClick = { homeScreenViewModel.toggleLike(it) }
                )
                SectionHeader(
                    title = "Abril",
                    textColor = primaryColor,
                    fontSize = 27.48.sp,
                    onClick = { navigateTo(Routes.FashionNewsScreen)}
                )

                HomeBannerStatic(
                    banner = HeroBannerData(
                        imageRes = R.drawable.home_novedades_mes,
                        label = "NOVEDADES",
                        title = "El talento que está\ncambiando la moda.",
                        subtitle = "Disponible ya en BoostAR.",
                    ),
                    onButtonClick = { navigateTo(Routes.FashionNewsScreen) }
                )

                SectionHeader(
                    title = "Licencias",
                    textColor = Color.Black,
                    fontSize = 27.48.sp,
                    onClick = { navigateTo(Routes.LicenseScreen)},
                    enabled = false
                )

                LicensesCarousel(
                    collabs = collabs,
                    onItemClick = {}
                )

                SectionHeader(
                    title = "Nuevos Partners",
                    onClick = { navigateTo(Routes.NewPartnerScreen) }
                )

                PartnerCarousel(
                    partners = partners,
                    onItemClick = {}
                )

                SectionHeader(
                    title = "Eventos",
                    onClick = { navigateTo(Routes.EventScreen) }
                )

                event?.let { event ->
                    EventBanner(
                        name = event.bannerName,
                        description = event.bannerDescription,
                        media = event.bannerMedia,
                        isMain = event.isMain,
                        onClick = { navigateTo(Routes.EventScreen) }
                    )

                }
                SectionHeader(
                    title = "Ofertas",
                    textColor = Color.Red,
                    arrowColor = Color.Red,
                    onClick = { navigateTo(Routes.HomeScreen) },
                    enabled = false
                )
                ProductCarrousel(
                    products = productsDiscounts,
                    onItemClick = { productId -> navigateTo(Routes.FeedScreen(productId, SortOrder.DISCOUNT,
                        ProductFilters(discount = true))) },
                    onLikeClick = { homeScreenViewModel.toggleLike(it) }
                )

            }
        })

}