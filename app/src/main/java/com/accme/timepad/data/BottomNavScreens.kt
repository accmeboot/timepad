package com.accme.timepad.data

import com.accme.timepad.R

sealed class BottomNavScreens(
    val route: String,
    val icon: Int,
    val selectedIcon: Int
    ) {
    object Home : BottomNavScreens(
        "home",
        R.drawable.ic_timer,
        R.drawable.ic_timer_selected
    )

    object Stats : BottomNavScreens(
        "stats",
        R.drawable.ic_stats,
        R.drawable.ic_stats_selected
    )

    object Pad : BottomNavScreens(
        "pad",
        R.drawable.ic_pluse,
        R.drawable.ic_pluse
    )
}
