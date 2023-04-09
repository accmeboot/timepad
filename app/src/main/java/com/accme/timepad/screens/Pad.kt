package com.accme.timepad.screens

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.accme.timepad.R
import com.accme.timepad.composables.AppTextField
import com.accme.timepad.composables.DateTimePickerCard
import com.accme.timepad.viewModels.AppViewModel
import com.accme.timepad.viewModels.PadViewModel
import com.commandiron.wheel_picker_compose.WheelDateTimePicker
import com.commandiron.wheel_picker_compose.WheelTimePicker
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.LocalTime

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Pad(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    appViewModel: AppViewModel = viewModel(),
    padViewModel: PadViewModel = viewModel()
) {
    val scope = rememberCoroutineScope()
    var taskTitle by remember {
        mutableStateOf("")
    }
    var duration by remember {
        mutableStateOf(LocalTime.now())
    }

    var date by remember {
        mutableStateOf(LocalDateTime.now())
    }

    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier
            .fillMaxHeight(0.65f)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.pad_header)) },
                backgroundColor = MaterialTheme.colors.background,
                actions = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                appViewModel.modalBottomSheetState.hide()
                            }
                        },
                    ) {
                        Icon(
                            modifier = Modifier.rotate(270F),
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Close bottom sheet",
                        )
                    }
                },
            )
        }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .padding(horizontal = 16.dp),
        ) {
            AppTextField(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth(),
                value = taskTitle,
                label = stringResource(id = R.string.pad_text_field_label),
                onValueChange = {value ->
                    taskTitle = value
                }
            )
            DateTimePickerCard(
                modifier = Modifier.padding(vertical = 10.dp),
                label = stringResource(id = R.string.pad_duration)
            ) {
                WheelTimePicker(
                    startTime = LocalTime.of(1, 0),
                    maxTime = LocalTime.of(7,59),
                    onSnappedTime = {time ->
                        duration = time
                    },
                    textColor = MaterialTheme.colors.onBackground
                )
            }
            DateTimePickerCard(
                modifier = Modifier.padding(vertical = 10.dp),
                label = stringResource(id = R.string.pad_date)
            ) {
                WheelDateTimePicker(
                    minDateTime = LocalDateTime.now(),
                    onSnappedDateTime = {dateTime ->
                        date = dateTime

                    },
                    textColor = MaterialTheme.colors.onBackground
                )
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                onClick = {
                    scope.launch {
                        appViewModel.modalBottomSheetState.hide()
                    }

                    padViewModel.createTask(
                        name = taskTitle,
                        duration = duration,
                        date = date
                    )
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = MaterialTheme.colors.surface
                ),
                enabled = taskTitle.isNotEmpty()
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 10.dp),
                    text = stringResource(id = R.string.pad_create_btn_label),
                    color = MaterialTheme.colors.onBackground
                )
            }
        }
    }
}