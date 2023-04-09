package com.accme.timepad.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.accme.timepad.data.BottomNavScreens
import com.accme.timepad.viewModels.AppViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppBottomNav(
    navController: NavHostController,
    bottomNavScreens: List<BottomNavScreens>,
    appViewModel: AppViewModel = viewModel()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val scope = rememberCoroutineScope()

    Surface(
        elevation = 4.dp,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colors.secondary,
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 45.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            items(bottomNavScreens, key = { it.route }) { screen ->
                val route = stringResource(id = screen.route)
                val isCurrent = currentRoute == route
                val painter = painterResource(id = if (isCurrent) screen.selectedIcon else screen.icon)
                val isPad = screen.route == BottomNavScreens.Pad.route

                IconButton(
                    modifier = Modifier.size(50.dp),
                    onClick = {
                        if (isPad) {
                            scope.launch {
                                appViewModel.modalBottomSheetState.show()
                            }
                        }

                        if (!isCurrent && !isPad) {
                            navController.navigate(route)
                        }
                    }
                ) {
                    Icon(
                        painter,
                        tint = if (isPad) Color.Unspecified else MaterialTheme.colors.onBackground,
                        contentDescription = "Bottom navigation button with label $route"
                    )
                }
            }
        }
    }
}