package com.example.boostar_uaal.ui.screen.eventScreen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.core.components.AdaptiveFeedLayout
import com.example.boostar_uaal.core.components.BottomNavBar
import com.example.boostar_uaal.core.components.InterText
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.ui.screen.gameScreen.components.DailyGoalsSection
import com.example.boostar_uaal.ui.screen.gameScreen.components.ForYouSection
import com.example.boostar_uaal.ui.screen.gameScreen.components.GameHeader
import com.example.boostar_uaal.ui.screen.gameScreen.components.GameUserInformationCard
import com.example.boostar_uaal.ui.screen.gameScreen.components.KnowledgeSection
import com.example.boostar_uaal.ui.screen.gameScreen.components.LessonSection

@Composable
fun EventHeader(modifier: Modifier = Modifier){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        InterText(
            text = "Eventos",
            fontSize = 38.54.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }

}