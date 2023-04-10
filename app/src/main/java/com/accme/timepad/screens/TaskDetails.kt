package com.accme.timepad.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.accme.timepad.R
import com.accme.timepad.viewModels.TaskDetailViewModel

@Composable
fun TaskDetails(
    modifier: Modifier = Modifier,
    navController: NavController,
    taskId: String?,
    taskDetailViewModel: TaskDetailViewModel,
) {
    val task = taskDetailViewModel.task?.collectAsState()
    var isLoading by remember {
        mutableStateOf(true)
    }
    val currentCount = taskDetailViewModel.currentCount.collectAsState()

    LaunchedEffect(true) {
        if (taskId != null) {
            isLoading = true
            taskDetailViewModel.loadTask(taskId.toInt())
        }
    }

    LaunchedEffect(key1 = task != null) {
        isLoading = false
    }

    AnimatedVisibility(
        visible = !isLoading,
        enter = slideInHorizontally(
            animationSpec = tween(200),
        ),
    ) {
        task?.value?.let {taskDetail ->
            Scaffold(
                modifier = modifier,
                topBar = {
                    TopAppBar(
                        title = { Text(text = taskDetail.name) },
                        backgroundColor = MaterialTheme.colors.background,
                        contentColor = MaterialTheme.colors.onBackground,
                        navigationIcon = {
                            IconButton(onClick = {
                                navController.popBackStack()
                            }) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Back to Home screen icon"
                                )
                            }
                        }
                    )
                }
            ) {
                Column(
                    modifier = Modifier
                        .padding(it)
                        .padding(horizontal = 18.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = currentCount.value,
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Surface(elevation = 1.dp, shape = CircleShape) {
                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape)
                                    .background(color = MaterialTheme.colors.secondary)
                                    .clickable {
                                        if (!taskDetailViewModel.isCounting.value) {
                                            taskDetailViewModel.startCountDown()
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(
                                        id =
                                        if (taskDetailViewModel.isCounting.value) R.drawable.ic_pause
                                        else R.drawable.ic_play
                                    ),
                                    contentDescription = "Play/Pause button",
                                    tint = MaterialTheme.colors.onBackground
                                )
                            }
                        }

                        Surface(elevation = 1.dp, shape = CircleShape) {
                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape)
                                    .background(color = MaterialTheme.colors.secondary)
                                    .clickable {
                                        taskDetailViewModel.stopCountDown()
                                    },
                                contentAlignment = Alignment.Center,
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_stop),
                                    contentDescription = "Play/Pause button",
                                    tint = MaterialTheme.colors.onBackground
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.fillMaxHeight(0.1f))
                }
            }
        }
    }
}
