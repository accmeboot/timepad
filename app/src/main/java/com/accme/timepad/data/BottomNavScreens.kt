package com.accme.timepad.data

import androidx.annotation.StringRes
import com.accme.timepad.R

sealed class BottomNavScreens(
    @StringRes val route: Int,
    val icon: Int,
    val selectedIcon: Int
    ) {
    object Home : BottomNavScreens(
        R.string.home_route,
        R.drawable.ic_timer,
        R.drawable.ic_timer_selected
    )

    object Stats : BottomNavScreens(
        R.string.stats_route,
        R.drawable.ic_stats,
        R.drawable.ic_stats_selected
    )

    object Pad : BottomNavScreens(
        R.string.pad_route,
        R.drawable.ic_pluse,
        R.drawable.ic_pluse
    )
}