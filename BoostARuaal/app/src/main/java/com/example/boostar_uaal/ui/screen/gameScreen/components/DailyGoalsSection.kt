package com.example.boostar_uaal.ui.screen.gameScreen.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable

@Composable
fun DailyGoalsSection(){
    DailyGoalsHeader()
    DailyProgressCard()
    LazyColumn() {
        /*
        items(){
            DailyGoalItem()
        }
        */
    }
}