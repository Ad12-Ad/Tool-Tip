package com.example.tooltip.core.utlis

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt

fun String?.toComposeColor(default: Color = Color.Gray): Color {
    if (this.isNullOrEmpty()) return default
    return try {
        Color(this.toColorInt())
    } catch (e: Exception) {
        default
    }
}