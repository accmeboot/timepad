package com.accme.timepad.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.accme.timepad.data.BottomNavScreens
import com.accme.timepad.screens.Home
import com.accme.timepad.screens.Stats

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = stringResource(id = BottomNavScreens.Home.route),
) {
    val homeRoute = stringResource(id = BottomNavScreens.Home.route)
    val statsRoute = stringResource(id = BottomNavScreens.Stats.route)

    AppNavWrapper(navController) {padding ->
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            composable(homeRoute) {
                    Home(modifier = Modifier.padding(padding), navController)
            }

            composable(statsRoute) {
                    Stats(modifier = Modifier.padding(padding), navController)
            }
        }
    }
}