package com.example.boostar_uaal.core.utils

import android.icu.text.DecimalFormat

fun Long.formatLikes(): String {
    val df = DecimalFormat("#.#")
    return when {
        this < 1000 -> this.toString()
        this < 1_000_000 -> "${df.format(this / 1000.0)}k"
        this < 1_000_000_000 -> "${df.format(this / 1_000_000.0)}M"
        else -> "${df.format(this / 1_000_000_000.0)}B"
    }
}