package com.example.boostar_uaal.ui.screen.licenseScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.core.components.AdaptiveFeedLayout
import com.example.boostar_uaal.core.components.BackButtonHeader
import com.example.boostar_uaal.core.components.BottomNavBar
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.ui.screen.licenseScreen.components.LicenseSection


@Composable
fun LicenseScreen(back: () -> Unit, navigateTo: (Routes) -> Unit){
    val licenseScreenViewModel = viewModel<LicenseScreenViewModel>()
    val licenses by licenseScreenViewModel.license.collectAsState()
    LaunchedEffect(Unit) {
        licenseScreenViewModel.loadLicenses()
        licenseScreenViewModel.refreshLikes()
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
                            title = "Licencias",
                            onBackClick = { back() }
                        )
                    }
                    items(licenses) { license ->
                        LicenseSection(
                            license = license,
                            products =  emptyList(),
                            onItemClick = { navigateTo(Routes.FeedScreen(it))},
                            onLikeClick = { licenseScreenViewModel.toggleLike(it) },
                            callback = { licenseScreenViewModel.loadProducts(it) }
                        )

                    }

                }
            }
        }
    )
}