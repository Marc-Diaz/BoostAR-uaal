package com.example.boostar_uaal.ui.screen.profileScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.core.components.AdaptiveFeedLayout
import com.example.boostar_uaal.core.components.BottomNavBar
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.ui.screen.profileScreen.components.ProfileHeader
import com.example.boostar_uaal.ui.screen.profileScreen.components.StatusCard

@Composable
fun ProfileScreen(navigateTo: (Routes) -> Unit, back: () -> Unit, backTo: (Routes) -> Unit){
    val profileScreenViewModel = viewModel<ProfileScreenViewModel>()
    val user by profileScreenViewModel.user.collectAsState()
    val cardData by profileScreenViewModel.cardData.collectAsState()
    LaunchedEffect(Unit) {
        profileScreenViewModel.initializeProfileScreen()
    }
    Scaffold(
        bottomBar = { BottomNavBar(navigateTo, Routes.ProfileScreen) },
        content = { paddingValues ->
            AdaptiveFeedLayout(Modifier.padding(paddingValues)) {
                ProfileHeader(
                    name = user?.name ?: "",
                    mail = user?.mail ?: "",
                    picture = user?.picture ?:"https://lh3.googleusercontent.com/a/ACg8ocJHT7IO5QH47WjEdV4XTGqSIk_6-oO2pN3HNxGXcUDSF7cZgIQ=s96-c",
                    level = 12
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(cardData) { card ->
                        StatusCard(cardData = card)
                    }
                }

            }
        }
    )



}