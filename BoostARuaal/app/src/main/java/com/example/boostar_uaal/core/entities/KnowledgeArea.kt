package com.example.boostar_uaal.core.entities

import androidx.compose.ui.graphics.Color

data class KnowledgeArea(
    val id: Int,
    val title: String,
    val percentage: Int,
    val color: Color,
    val image: Int
)