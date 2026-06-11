package dam_A51706.finalproject.ui.screens

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dam_A51706.finalproject.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit,
    initialDateMillis: Long? = null
) {
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = initialDateMillis)

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text(stringResource(R.string.ok),
                    style = MaterialTheme.typography.labelMedium,
                    color = colorScheme.secondary)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel),
                    style = MaterialTheme.typography.labelMedium,
                    color = colorScheme.secondary)
            }
        },

        ) {
        DatePicker(
            state = datePickerState,
            title = {Text(stringResource(R.string.select_date),
                style = MaterialTheme.typography.labelMedium,
                color = colorScheme.tertiary,
                modifier = Modifier.padding(25.dp))},

            colors = DatePickerDefaults.colors(
                containerColor = colorScheme.surface,
                headlineContentColor = colorScheme.tertiary,
                navigationContentColor = colorScheme.tertiary,
                dateTextFieldColors = TextFieldDefaults.colors(
                    unfocusedContainerColor = colorScheme.onPrimary,
                    focusedContainerColor = colorScheme.onPrimary,
                    unfocusedIndicatorColor = colorScheme.primary,
                    focusedIndicatorColor = colorScheme.secondary,
                    unfocusedTextColor = colorScheme.tertiary,
                    focusedTextColor = colorScheme.tertiary
                )
            )
        )
    }
}


@Composable
fun DatePickerFieldToModal(
    modifier: Modifier = Modifier,
    selectedDateMillis: Long?,
    onDateSelected: (Long?) -> Unit
) {
    var showModal by remember { mutableStateOf(false) }

    TextField(
        value = selectedDateMillis?.let { convertMillisToDate(it) } ?: "",
        onValueChange = { },
        label = {
            Text(
                stringResource(R.string.deadline),
                style = MaterialTheme.typography.bodyLarge,
                color = colorScheme.tertiary,
                fontWeight = FontWeight.Bold
            )
        },
        placeholder = { Text("DD/MM/YYYY") },
        trailingIcon = {
            Icon(
                Icons.Default.DateRange,
                contentDescription = stringResource(R.string.select_date),
                tint = colorScheme.tertiary
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = colorScheme.onPrimary,
            focusedContainerColor = colorScheme.onPrimary,
            unfocusedIndicatorColor = colorScheme.primary,
            focusedIndicatorColor = colorScheme.secondary,
            unfocusedTextColor = colorScheme.tertiary,
            focusedTextColor = colorScheme.tertiary
        ),
        modifier = modifier
            .pointerInput(selectedDateMillis) {
                awaitEachGesture {
                    awaitFirstDown(pass = PointerEventPass.Initial)
                    val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                    if (upEvent != null) {
                        showModal = true
                    }
                }
            }
    )
    if (showModal) {
        DatePickerModal(
            initialDateMillis = selectedDateMillis,
            onDateSelected = onDateSelected,
            onDismiss = { showModal = false }
        )
    }
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

