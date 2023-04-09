package com.accme.timepad.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.accme.timepad.composables.TaskRow
import com.accme.timepad.viewModels.HomeViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Home(
    modifier: Modifier = Modifier,
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val tasks by homeViewModel.tasks.collectAsState()
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp

    LaunchedEffect(key1 = null) {
        homeViewModel.loadTasks()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
                 TopAppBar(
                     title = { Text(text = "Home") },
                     backgroundColor = MaterialTheme.colors.background,
                     contentColor = MaterialTheme.colors.onBackground
                 )
        },
        backgroundColor = MaterialTheme.colors.background
    ) {
        AnimatedVisibility(
            visible = tasks.isNotEmpty(),
            enter = slideInVertically(
                animationSpec = tween(200),
                initialOffsetY = { it - screenHeight }
            ),
            exit = slideOutVertically(animationSpec = tween(200))
        ) {
            LazyColumn(modifier = Modifier.padding(it)) {
                items(tasks) {task ->
                    TaskRow(task = task)
                }
            }
        }

        AnimatedVisibility(
            visible = tasks.isEmpty(),
            enter = scaleIn(animationSpec = tween(200)),
            exit = scaleOut(animationSpec = tween(200))
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CircularProgressIndicator()
            }
        }

    }
}