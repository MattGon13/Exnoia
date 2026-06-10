package dam_A51706.finalproject.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import dam_A51706.finalproject.R
import dam_A51706.finalproject.data.model.Step
import dam_A51706.finalproject.ui.theme.ExnoiaAppTheme

@Composable
fun MinimalDialog(
    step: Step? = null,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    onDelete: (() -> Unit)? = null
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = colorScheme.surface),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 10.dp, horizontal = 10.dp)
            ) {
                Text("New step",
                    style = MaterialTheme.typography.labelLarge,
                    color = colorScheme.tertiary,
                    modifier = Modifier.padding(top = 20.dp))

                TextField(
                    value = "",
                    onValueChange = {},
                    singleLine = true,
                    label = {
                        Text(
                            stringResource(R.string.title),
                            style = MaterialTheme.typography.labelLarge,
                            color = colorScheme.tertiary
                        )
                    },
                    textStyle = MaterialTheme.typography.labelLarge,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = colorScheme.onPrimary,
                        focusedContainerColor = colorScheme.onPrimary,
                        unfocusedIndicatorColor = colorScheme.primary,
                        focusedIndicatorColor = colorScheme.secondary,
                        unfocusedTextColor = colorScheme.tertiary,
                        focusedTextColor = colorScheme.tertiary
                    ),
                )

                Spacer(modifier = Modifier.weight(1F))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text(stringResource(R.string.cancel),
                            style = MaterialTheme.typography.labelMedium,
                            color = colorScheme.primary)
                    }
                    TextButton(
                        onClick = { onConfirmation() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text(
                            stringResource(R.string.confirm),
                            style = MaterialTheme.typography.labelMedium,
                            color = colorScheme.secondary)
                    }
                }
            }
        }
    }
}

@Composable
fun MinimalDialogPreview(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = colorScheme.surface),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 10.dp, horizontal = 10.dp)
            ) {
                Text("New step",
                    style = MaterialTheme.typography.labelLarge,
                    color = colorScheme.tertiary,
                    modifier = Modifier.padding(top = 20.dp))

                TextField(
                    value = "",
                    onValueChange = {},
                    singleLine = true,
                    label = {
                        Text(
                            stringResource(R.string.title),
                            style = MaterialTheme.typography.labelLarge,
                            color = colorScheme.tertiary
                        )
                    },
                    textStyle = MaterialTheme.typography.labelLarge,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = colorScheme.onPrimary,
                        focusedContainerColor = colorScheme.onPrimary,
                        unfocusedIndicatorColor = colorScheme.primary,
                        focusedIndicatorColor = colorScheme.secondary,
                        unfocusedTextColor = colorScheme.tertiary,
                        focusedTextColor = colorScheme.tertiary
                    ),
                )

                Spacer(modifier = Modifier.weight(1F))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text(stringResource(R.string.cancel),
                            style = MaterialTheme.typography.labelMedium,
                            color = colorScheme.primary)
                    }
                    TextButton(
                        onClick = { onConfirmation() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text(
                            stringResource(R.string.confirm),
                            style = MaterialTheme.typography.labelMedium,
                            color = colorScheme.secondary)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp,orientation=portrait")
@Composable
fun CreateStepPreview() {
    ExnoiaAppTheme() {
        MinimalDialogPreview({}, {})
    }
}
