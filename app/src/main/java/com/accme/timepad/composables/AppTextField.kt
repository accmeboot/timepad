package com.accme.timepad.composables

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    label: String = "",
    imeAction: ImeAction = ImeAction.Done,
    keyboardType: KeyboardType = KeyboardType.Text,
    onDone: () -> Unit = {},
    onValueChange: (String) -> Unit = {},
    focusManager: FocusManager = LocalFocusManager.current,
    clearOnDone: Boolean = true,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        label = { Text(text = label) },
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
            keyboardType = keyboardType
        ),
        keyboardActions = KeyboardActions(onDone = {
            onDone()
            if (clearOnDone) {
                focusManager.clearFocus()
            }
        }),
        shape = MaterialTheme.shapes.small,
    )
}