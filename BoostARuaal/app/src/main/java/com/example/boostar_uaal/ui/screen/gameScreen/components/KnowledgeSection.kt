package com.example.boostar_uaal.ui.screen.gameScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.components.InterText
import com.example.boostar_uaal.core.entities.KnowledgeArea
import com.example.boostar_uaal.core.theme.secondaryTextColor

@Composable
fun KnowledgeSection(knowledgeAreas: List<KnowledgeArea>){
    Column() {
        InterText(
            modifier = Modifier.padding(start = 48.dp),
            text = "ÁREAS DE CONOCIMIENTO",
            style = TextStyle(
                fontSize = 14.9.sp,
                fontWeight = FontWeight(700),
                color = secondaryTextColor,
            )
        )
        LazyRow() {
            items(knowledgeAreas){ area ->
                KnowledgeCard(area)

            }
        }
    }

}
@Preview(showBackground = true)
@Composable
fun PreviewKnowledgeSection(){
    val knowledgeAreas = listOf(
        KnowledgeArea(
            id = 1,
            title = "Fits",
            percentage = 25,
            color = Color(0xFF0080FF),
            image = R.drawable.boostar_logo
        ),
        KnowledgeArea(
            id = 2,
            title = "Color",
            percentage = 68,
            color = Color(0xFFFF00DD),
            image = R.drawable.heart_icon
        ),
        KnowledgeArea(
            id = 3,
            title = "Estilo",
            percentage = 43,
            color = Color(0xFFFFD900),
            image = R.drawable.basket_icon
        )
    )
    KnowledgeSection(knowledgeAreas)
}