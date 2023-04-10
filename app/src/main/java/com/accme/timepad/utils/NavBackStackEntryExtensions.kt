package com.accme.timepad.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

@Composable
inline fun <reified VM: ViewModel> NavBackStackEntry.withViewModel(
    navController: NavHostController,
    route: String
): VM {
    val parentEntry = remember(this) {
        navController.getBackStackEntry(route)
    }

    return hiltViewModel<VM>(parentEntry)
}
