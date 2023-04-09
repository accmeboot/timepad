package com.accme.timepad.composables

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.accme.timepad.ui.theme.TimepadTheme

@Composable
fun App(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    TimepadTheme {
        Surface(modifier, content = content)
    }
}
