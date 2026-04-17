package com.example.boostar_uaal.ui.screen.newPartnerScreens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.core.components.AdaptiveFeedLayout
import com.example.boostar_uaal.core.components.BackButtonHeader
import com.example.boostar_uaal.core.components.BottomNavBar
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.ui.screen.newPartnerScreens.components.PartnerSection
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import com.example.boostar_uaal.data.models.ProductFilters

@Composable
fun NewPartnerScreen(navigateTo: (Routes) -> Unit, back: () -> Unit){
    val newPartnerScreenViewModel = viewModel<NewPartnerScreenViewModel>()
    val partners by newPartnerScreenViewModel.partners.collectAsState()
    val products by newPartnerScreenViewModel.products.collectAsState()
    LaunchedEffect(Unit) {
        newPartnerScreenViewModel.loadPartners()
        newPartnerScreenViewModel.refreshLikes()
    }
    Scaffold(
        bottomBar = {
            BottomNavBar(
                naviagetTo = { navigateTo(it) },
                currentRoute = Routes.NewPartnerScreen
            )
        },
        content = { paddingValues ->
            AdaptiveFeedLayout(Modifier) {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    item {
                        BackButtonHeader(
                            Modifier.padding(top = paddingValues.calculateTopPadding()),
                            title = "Nuevos Partners",
                            onBackClick = { back() }
                        )
                    }
                    items(partners) { partner ->
                        PartnerSection(
                            partner = partner,
                            products = products[partner.id] ?: emptyList(),
                            onItemClick = {
                                navigateTo(Routes.FeedScreen(
                                    productId = it,
                                    filters = ProductFilters(partnerId = partner.id)
                                )
                                )},
                            onLikeClick = { newPartnerScreenViewModel.toggleLike(it) },
                            callback = { newPartnerScreenViewModel.loadProducts(it) }
                        )

                    }

                }
            }
        }
    )
}