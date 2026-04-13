package com.example.boostar_uaal.ui.screen.eventScreen.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.boostar_uaal.core.entities.Event

import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier

@Composable
fun EventList(modifier: Modifier = Modifier, items: List<Event>, onTryArClick: (String) -> Unit){
    LazyColumn(modifier = modifier) {
        items(items) { event ->
            Event(
                event = event,
                onTryArClick = { onTryArClick(event.model) })
        }
    }
}