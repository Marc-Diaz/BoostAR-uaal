package com.example.boostar_uaal.ui.screen.eventScreen

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
import com.example.boostar_uaal.ui.screen.eventScreen.components.Event
import com.example.boostar_uaal.ui.screen.eventScreen.components.EventHeader


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
            AdaptiveFeedLayout(Modifier) {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    item {
                        BackButtonHeader(
                            Modifier.padding(top = paddingValues.calculateTopPadding()),
                            title = "Eventos",
                            onBackClick = { back() }
                        )
                    }
                    items(events) { event ->
                        Event(
                            event = event,
                            onTryArClick = { navigateTo(Routes.ArScreen(event.model)) }
                        )

                    }

                }
            }
        }
    )
}