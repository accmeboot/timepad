package com.accme.timepad.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.accme.timepad.data.BottomNavScreens
import com.accme.timepad.screens.Home
import com.accme.timepad.screens.Stats
import com.accme.timepad.screens.TaskDetails
import com.accme.timepad.utils.Constants
import com.accme.timepad.utils.withViewModel

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = BottomNavScreens.Home.route,
) {
    AppNavWrapper(navController) {padding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            route = Constants.RootRoutes.MAIN.name
        ) {
            composable(BottomNavScreens.Home.route) {
                Home(
                    modifier = Modifier.padding(padding),
                    navController,
                    homeViewModel = it.withViewModel(navController, route = Constants.RootRoutes.MAIN.name)
                )
            }

            composable("${Constants.Routes.TASK_DETAIL.name}/{task_id}") {
                TaskDetails(
                    modifier = Modifier.padding(padding),
                    navController = navController,
                    taskId = it.arguments?.getString("task_id"),
                    taskDetailViewModel = it.withViewModel(navController, route = Constants.RootRoutes.MAIN.name)
                )
            }

            composable(BottomNavScreens.Stats.route) {
                    Stats(modifier = Modifier.padding(padding), navController)
            }
        }
    }
}

