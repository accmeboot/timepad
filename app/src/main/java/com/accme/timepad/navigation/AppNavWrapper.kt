package com.accme.timepad.navigation

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.accme.timepad.composables.AppBottomNav
import com.accme.timepad.data.BottomNavScreens
import com.accme.timepad.screens.Pad
import com.accme.timepad.viewModels.AppViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppNavWrapper(
    navController: NavHostController,
    appViewModel: AppViewModel = viewModel(),
    items: List<BottomNavScreens> = listOf(
        BottomNavScreens.Home,
        BottomNavScreens.Pad,
        BottomNavScreens.Stats,
    ),
    content: @Composable (PaddingValues) -> Unit
) {
    val focusManager = LocalFocusManager.current

    LaunchedEffect(key1 = appViewModel.modalBottomSheetState.currentValue) {
        if (appViewModel.modalBottomSheetState.currentValue == ModalBottomSheetValue.Hidden) {
            focusManager.clearFocus()
        }
    }

    ModalBottomSheetLayout(
        sheetContent = { Pad(navController = navController) },
        sheetState = appViewModel.modalBottomSheetState,
        sheetShape = MaterialTheme.shapes.medium
    ) {
        Scaffold(
            modifier = Modifier.pointerInput(Unit) {
                detectTapGestures(onTap = {
                    if (appViewModel.modalBottomSheetState.currentValue == ModalBottomSheetValue.Hidden) {
                        focusManager.clearFocus()
                    }
                })
            },
            bottomBar = { AppBottomNav(navController, items) },
        ) {
            content(it)
        }
    }
}
