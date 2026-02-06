package com.example.boostar_uaal.ui.screen.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.boostar_uaal.ui.screen.homeScreen.components.ItemCarrousel
import com.example.boostar_uaal.navigation.Routes
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.ui.screen.homeScreen.components.HomeHeader
import com.example.boostar_uaal.ui.screen.homeScreen.components.HomeHero
import com.example.boostar_uaal.ui.screen.homeScreen.components.HomeNav

@Composable
fun HomeScreen(navigateTo: (Routes) -> Unit){
    val homeScreenViewModel = viewModel<HomeScreenViewModel>()
    val products by homeScreenViewModel.products.collectAsState()

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
        HomeHeader()
        HomeNav()
        HomeHero()
        ItemCarrousel(
            name = "Para ti",
            products = products,
            navigateTo = {navigateTo(Routes.HomeScreen)},
            itemClickable = { navigateTo(Routes.FeedScreen(it)) }
        )
        ItemCarrousel(
            name = "Tendencias",
            products = products,
            navigateTo = {navigateTo(Routes.HomeScreen)},
            itemClickable = { navigateTo(Routes.FeedScreen(it)) }
        )

    }
}
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
        HomeScreen({})
}