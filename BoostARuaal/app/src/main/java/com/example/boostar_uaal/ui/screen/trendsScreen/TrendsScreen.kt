package com.example.boostar_uaal.ui.screen.trendsScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.components.AdaptiveFeedLayout
import com.example.boostar_uaal.core.components.BackButtonHeader
import com.example.boostar_uaal.core.components.BottomNavBar
import com.example.boostar_uaal.core.components.HomeBannerStatic
import com.example.boostar_uaal.core.components.InterText
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.data.models.SortOrder
import com.example.boostar_uaal.ui.screen.trendsScreen.components.RankedItemCarrousel
import com.example.boostar_uaal.ui.screen.homeScreen.components.CollabCarousel
import com.example.boostar_uaal.ui.screen.homeScreen.components.HeroBannerData
import com.example.boostar_uaal.ui.screen.homeScreen.components.ItemCarrousel


@Composable
fun TrendsScreen(navigateTo: (Routes) -> Unit) {
    val trendsScreenViewModel = viewModel<TrendsScreenViewModel>()
    val trendingProducts by trendsScreenViewModel.productsTrends.collectAsState()
    val productsTrends by trendsScreenViewModel.productsTrends.collectAsState()
    val collabs by trendsScreenViewModel.collabs.collectAsState()

    LaunchedEffect(Unit) {
        trendsScreenViewModel.initializeFashionNews()
    }

    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = {
            BottomNavBar(
                naviagetTo = { navigateTo(it) },
                currentRoute = Routes.TendenciasScreen
            )
        },
        content = { paddingValues ->

            AdaptiveFeedLayout() {

                BackButtonHeader(
                    Modifier.padding(top = paddingValues.calculateTopPadding()),
                    title = "Novedades",
                    onBackClick = { navigateTo(Routes.HomeScreen) }
                )
                HomeBannerStatic(
                    banner = HeroBannerData(
                        imageRes = R.drawable.home_novedades_mes,
                        label = "NOVEDADES",
                        title = "El talento que está\ncambiando la moda.",
                        subtitle = "Disponible ya en BoostAR.",
                    ),
                    onButtonClick = { }
                )
                InterText(
                    AnnotatedString.fromHtml("Boostar Top 10<span style=\"color:#007AFF\">.</span>"),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp),

                    )

                RankedItemCarrousel(
                    products = trendingProducts,
                    onItemClick = { productId ->

                    }
                )

                InterText(
                    AnnotatedString.fromHtml("Estilos Tendencia<span style=\"color:#007AFF\">.</span>"),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp),

                    )

                InterText(
                    AnnotatedString.fromHtml("Ranking Viral<span style=\"color:#007AFF\">.</span>"),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp),

                    )
                ItemCarrousel(
                    products = productsTrends,
                    onItemClick = { productId ->
                        navigateTo(
                            Routes.FeedScreen(
                                productId,
                                SortOrder.TRENDS
                            )
                        )
                    },
                    onLikeClick = { trendsScreenViewModel.toggleLike(it) }
                )
                InterText(
                    AnnotatedString.fromHtml("Top Licencias Ahora<span style=\"color:#007AFF\">.</span>"),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp),

                    )
                CollabCarousel(
                    collabs = collabs,
                    onItemClick = { collabId ->
                    }
                )
            }


        }
    )


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ParaTiScreenPreview() {
    TrendsScreen(
        navigateTo = { }
    )
}
