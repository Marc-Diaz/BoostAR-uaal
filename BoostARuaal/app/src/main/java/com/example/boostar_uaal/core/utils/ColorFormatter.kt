package com.example.boostar_uaal.core.utils

import androidx.compose.ui.graphics.Color

data class RGBA(val r: Int, val g: Int, val b: Int, val a: Int = 255)

class ColorFormatter(){


    fun getColor(hex: String): Color {
        val rgba = parseHexColor(hex)
        return getColor(rgba)
    }
    fun getColor(rgba: RGBA): Color {
        return Color(red = rgba.r, green = rgba.g, blue = rgba.b, alpha = rgba.a)
    }

    fun parseHexColor(hex: String): RGBA {
        val cleanHex = hex.removePrefix("#")

        return when (cleanHex.length) {
            6 -> { // RRGGBB
                val r = cleanHex.substring(0, 2).toInt(16)
                val g = cleanHex.substring(2, 4).toInt(16)
                val b = cleanHex.substring(4, 6).toInt(16)
                RGBA(r, g, b)
            }
            8 -> { // AARRGGBB
                val a = cleanHex.substring(0, 2).toInt(16)
                val r = cleanHex.substring(2, 4).toInt(16)
                val g = cleanHex.substring(4, 6).toInt(16)
                val b = cleanHex.substring(6, 8).toInt(16)
                RGBA(r, g, b, a)
            }
            else -> throw IllegalArgumentException("Invalid Color Format")
        }
    }
}
