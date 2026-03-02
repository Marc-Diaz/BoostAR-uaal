package com.example.boostar_uaal.core.entities

import androidx.compose.ui.graphics.Color

data class DropData(
    val id: Int,
    val imageRes: Int,
    val status: String,
    val statusColor: Color,
    val title: String,
    val collection: String,
    val targetTimestamp: Long,
    val isNotified: Boolean = false
)
