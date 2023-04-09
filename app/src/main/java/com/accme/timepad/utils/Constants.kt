package com.accme.timepad.utils

import androidx.compose.ui.graphics.Color

object Constants {
    private val taskLogoColors = listOf(
        Color(0xFF9B51E0),
        Color(0xFFFFA656),
        Color(0xFFFD5B71),
        Color(0xFF07E092)
    )

    const val TIME_FORMAT = "HH:mm"
    const val FULL_DATE_FORMAT = "E d, LLLL u $TIME_FORMAT"

    fun getRandomColor(): Color {
        return taskLogoColors.random()
    }
}