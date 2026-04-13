package com.example.boostar_uaal.ui.screen.eventScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.core.components.AdaptiveFeedLayout
import com.example.boostar_uaal.core.components.BottomNavBar
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.ui.screen.eventScreen.components.EventHeader
import com.example.boostar_uaal.ui.screen.eventScreen.components.EventList


@Composable
fun EventScreen(navigateTo: (Routes) -> Unit, back: () -> Unit, backTo: () -> Unit){
    val eventScreenViewModel = viewModel<EventScreenViewModel>()
    val events by eventScreenViewModel.events.collectAsState()

    LaunchedEffect(Unit) {
        eventScreenViewModel.initializeEvents()
    }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                naviagetTo = { navigateTo(it) },
                currentRoute = Routes.GameScreen
            )
        },
        content = { paddingValues ->
            AdaptiveFeedLayout {
                EventHeader(Modifier.padding(top = paddingValues.calculateTopPadding()))
                EventList(
                    modifier = Modifier,
                    items = events,
                    onTryArClick = { navigateTo(Routes.ArScreen(it)) }
                )

            }
        }
    )
}