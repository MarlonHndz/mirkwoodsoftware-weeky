package com.mirkwoodsoftware.presentation.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.mirkwoodsoftware.presentation.R
import com.mirkwoodsoftware.presentation.utils.DateUtils.convertUtcToLocal
import com.mirkwoodsoftware.presentation.utils.DateUtils.dd_MM_yyyy
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateInput(
    modifier: Modifier,
    onValueChange: (String) -> Unit,
    label: String
) {
    var isDatePickerVisible by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState(
        yearRange = (2023..2030),
        initialDisplayMode = DisplayMode.Picker,
        initialSelectedDateMillis = Date().time
    )

    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertUtcToLocal(it, dd_MM_yyyy)
    }
    val colors = TextFieldDefaults.outlinedTextFieldColors(
        disabledTextColor = MaterialTheme.colorScheme.onSurface,
        disabledBorderColor = MaterialTheme.colorScheme.outline,
        disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
        //For Icons
        disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
    )

    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = selectedDate.toString(),
                onValueChange = {
                    onValueChange(it)
                    isDatePickerVisible = false
                },
                label = { Text(label) },
                trailingIcon = {
                    IconButton(
                        onClick = { isDatePickerVisible = !isDatePickerVisible }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.DateRange,
                            contentDescription = null,
                            tint = androidx.compose.material.MaterialTheme.colors.primary
                        )
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth()
//                    .clickable {
//                        isDatePickerVisible = !isDatePickerVisible
//                    }
                ,
                enabled = false,
                colors = colors
            )
        }
        if (isDatePickerVisible) {
            DatePickerDialog(
                onDismissRequest = { isDatePickerVisible = false },
                confirmButton = {
                    TextButton(onClick = { isDatePickerVisible = false }) {
                        Text(stringResource(R.string.date_picker_confirm_button))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { isDatePickerVisible = false }) {
                        Text(stringResource(R.string.date_picker_dismiss_button))
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}
