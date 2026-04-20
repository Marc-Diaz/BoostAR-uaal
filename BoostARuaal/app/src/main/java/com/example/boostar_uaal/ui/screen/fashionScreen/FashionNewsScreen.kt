package com.example.boostar_uaal.ui.screen.fashionScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.components.AdaptiveFeedLayout
import com.example.boostar_uaal.core.components.BackButtonHeader
import com.example.boostar_uaal.core.components.BottomNavBar
import com.example.boostar_uaal.core.components.SectionHeader
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.ui.screen.fashionScreen.components.DropBannerStatic
import com.example.boostar_uaal.ui.screen.fashionScreen.components.DropsSingleColumnGrid
import com.example.boostar_uaal.ui.screen.fashionScreen.components.EmergingPartnersSection
import com.example.boostar_uaal.ui.screen.fashionScreen.components.ProductsGrid
import com.example.boostar_uaal.core.entities.BannerData

@Composable
fun FashionNewsScreen(navigateTo: (Routes) -> Unit) {
    val fashionNewsScreenViewModel = viewModel<FashionNewsScreenViewmodel>()
    val productList by fashionNewsScreenViewModel.productsTrends.collectAsState()


    LaunchedEffect(Unit) {
        fashionNewsScreenViewModel.initializeFashionNews()
    }

    Scaffold(

        containerColor = Color.Transparent,
        bottomBar = {
            BottomNavBar(
                naviagetTo = { navigateTo(it) },
                currentRoute = Routes.FashionNewsScreen
            )
        },
        content = { paddingValues ->

            AdaptiveFeedLayout {

                BackButtonHeader(
                    Modifier.padding(top = paddingValues.calculateTopPadding()),
                    title = "Novedades",
                    onBackClick = { navigateTo(Routes.HomeScreen) }
                )

                DropBannerStatic(
                    banner = BannerData(
                        imageRes = R.drawable.chica_novedades,
                        label = "NOVEDADES",
                        title = "El talento que está\ncambiando la moda.",
                        subtitle = "Disponible ya en BoostAR.",
                    ),
                    onTryArClick = { },
                    onReserveClick = { }

                )

                SectionHeader(
                    title = "Partners emergentes",
                    onClick = { navigateTo(Routes.FashionNewsScreen) },
                    enabled = false
                )

                ProductsGrid(
                    products = productList,
                    onProductClick = {}
                )

                SectionHeader(
                    title = AnnotatedString.fromHtml("<span style=\"color:#007AFF\">.</span>"),
                    onClick = {  },
                    enabled = false
                )

                EmergingPartnersSection(skeletonCount = 4)

                SectionHeader(
                    title = "Próximos drops",
                    onClick = {  }
                )
                DropsSingleColumnGrid(
                    drops = fashionNewsScreenViewModel.dropsHardcodeados,
                    onReserveClick = { },
                    onBellClick = { }
                )
            }

        }

    )
}

