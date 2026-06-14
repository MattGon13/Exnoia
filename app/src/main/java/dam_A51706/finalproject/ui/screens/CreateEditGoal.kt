package dam_A51706.finalproject.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dam_A51706.finalproject.R
import dam_A51706.finalproject.data.model.DifficultyLevel
import dam_A51706.finalproject.ui.theme.ExnoiaAppTheme
import dam_A51706.finalproject.viewmodel.GoalViewModel
import java.util.Date

@Composable
fun CreateEditGoalPortrait(
    goalViewModel: GoalViewModel,
    onBack: () -> Unit,
    onSaveSuccess: () -> Unit
) {
    val formState by goalViewModel.formState.collectAsState()

    Scaffold(
        // removed bottomBar
    ) { padding ->
        Surface(color = colorScheme.surface) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(vertical = 20.dp, horizontal = 10.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    IconButton(
                        onClick = onBack
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = stringResource(R.string.go_back),
                            tint = colorScheme.tertiary,
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                    }
                    IconButton(
                        onClick = {
                            goalViewModel.saveGoal(onComplete = onSaveSuccess)
                        }
                    ) {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = stringResource(R.string.edit),
                            tint = colorScheme.tertiary,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
                TextField(
                    value = formState.title,
                    onValueChange = { goalViewModel.updateForm(title = it) },
                    singleLine = true,
                    label = {
                        Text(
                            stringResource(R.string.title),
                            style = MaterialTheme.typography.labelLarge,
                            color = colorScheme.tertiary
                        )
                    },
                    textStyle = MaterialTheme.typography.bodyLarge,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = colorScheme.onPrimary,
                        focusedContainerColor = colorScheme.onPrimary,
                        unfocusedIndicatorColor = colorScheme.primary,
                        focusedIndicatorColor = colorScheme.secondary,
                        unfocusedTextColor = colorScheme.tertiary,
                        focusedTextColor = colorScheme.tertiary
                    ),
                )
                TextField(
                    value = formState.description,
                    onValueChange = { goalViewModel.updateForm(description = it) },
                    label = {
                        Text(
                            stringResource(R.string.description),
                            style = MaterialTheme.typography.bodyLarge,
                            color = colorScheme.tertiary,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    maxLines = 3,
                    textStyle = MaterialTheme.typography.bodyLarge,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = colorScheme.onPrimary,
                        focusedContainerColor = colorScheme.onPrimary,
                        unfocusedIndicatorColor = colorScheme.primary,
                        focusedIndicatorColor = colorScheme.secondary,
                        unfocusedTextColor = colorScheme.tertiary,
                        focusedTextColor = colorScheme.tertiary
                    ),
                )

                Spacer(modifier = Modifier.height(20.dp))

                DatePickerFieldToModal(
                    selectedDateMillis = formState.deadline?.time,
                    onDateSelected = { it?.let { date -> goalViewModel.updateForm(deadline = Date(date)) } }
                )

                TextField(
                    value = formState.reward,
                    onValueChange = { goalViewModel.updateForm(reward = it) },
                    singleLine = true,
                    label = {
                        Text(
                            stringResource(R.string.reward),
                            style = MaterialTheme.typography.bodyLarge,
                            color = colorScheme.tertiary,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    textStyle = MaterialTheme.typography.bodyLarge,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = colorScheme.onPrimary,
                        focusedContainerColor = colorScheme.onPrimary,
                        unfocusedIndicatorColor = colorScheme.primary,
                        focusedIndicatorColor = colorScheme.secondary,
                        unfocusedTextColor = colorScheme.tertiary,
                        focusedTextColor = colorScheme.tertiary
                    ),
                )

                DropdownInput(
                    options = DifficultyLevel.values().toList(),
                    selectedDifficulty = formState.difficulty,
                    onOptionSelected = { goalViewModel.updateForm(difficulty = it) }
                )

                Spacer(modifier = Modifier.height(50.dp))

                if (formState.goalId != null) {
                    Button(
                        onClick = {
                            goalViewModel.deleteGoal(formState.goalId!!) {
                                onBack()
                            }
                        },
                        modifier = Modifier
                            .width(200.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorScheme.secondary,
                            contentColor = colorScheme.onSecondary
                        ),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp)
                    ) {
                        Text("Delete",
                            style = MaterialTheme.typography.labelLarge,
                            color = colorScheme.tertiary)
                    }
                }
            }
        }
    }
}

@Composable
fun CreateEditGoalPortraitPreview() {
    Scaffold(
        bottomBar = { NavigationBar() },
    ) { padding ->
        Surface(color = MaterialTheme.colorScheme.surface) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 20.dp, horizontal = 10.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = stringResource(R.string.go_back),
                            tint = colorScheme.tertiary,
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                    }
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = stringResource(R.string.edit),
                            tint = colorScheme.tertiary,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
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
                TextField(
                    value = "",
                    onValueChange = {},
                    label = {
                        Text(
                            stringResource(R.string.description),
                            style = MaterialTheme.typography.bodyLarge,
                            color = colorScheme.tertiary,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    maxLines = 3,
                    textStyle = MaterialTheme.typography.bodyLarge,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = colorScheme.onPrimary,
                        focusedContainerColor = colorScheme.onPrimary,
                        unfocusedIndicatorColor = colorScheme.primary,
                        focusedIndicatorColor = colorScheme.secondary,
                        unfocusedTextColor = colorScheme.tertiary,
                        focusedTextColor = colorScheme.tertiary
                    ),
                )

                Spacer(modifier = Modifier.height(20.dp))

                //DatePickerFieldToModal()

                TextField(
                    value = "",
                    onValueChange = {},
                    singleLine = true,
                    label = {
                        Text(
                            stringResource(R.string.reward),
                            style = MaterialTheme.typography.bodyLarge,
                            color = colorScheme.tertiary,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    textStyle = MaterialTheme.typography.bodyLarge,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = colorScheme.onPrimary,
                        focusedContainerColor = colorScheme.onPrimary,
                        unfocusedIndicatorColor = colorScheme.primary,
                        focusedIndicatorColor = colorScheme.secondary,
                        unfocusedTextColor = colorScheme.tertiary,
                        focusedTextColor = colorScheme.tertiary
                    ),
                )

                //DropdownInput(listOf("Very easy", "Easy", "Medium", "Hard", "Very hard" ))

                Spacer(modifier = Modifier.height(50.dp))

                Button(
                    onClick = {},
                    modifier = Modifier
                        .width(200.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor =colorScheme.secondary,
                        contentColor = colorScheme.onSecondary
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp)
                ) {
                    Text(
                        stringResource(R.string.delete),
                        style = MaterialTheme.typography.labelLarge,
                        color = colorScheme.tertiary)
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownInput(
    options: List<DifficultyLevel>,
    selectedDifficulty: DifficultyLevel,
    onOptionSelected: (DifficultyLevel) -> Unit
){

    var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = {isExpanded = !isExpanded}
    ) {
        TextField(
            value = selectedDifficulty.name,
            onValueChange = {},
            readOnly = true,
            label = {
                Text(
                    stringResource(R.string.difficulty),
                    style = MaterialTheme.typography.bodyLarge,
                    color = colorScheme.tertiary,
                    fontWeight = FontWeight.Bold
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon((isExpanded))
            },
            modifier = Modifier.menuAnchor(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorScheme.onPrimary,
                focusedContainerColor = colorScheme.onPrimary,
                unfocusedIndicatorColor = colorScheme.primary,
                focusedIndicatorColor = colorScheme.secondary,
                unfocusedTextColor = colorScheme.tertiary,
                focusedTextColor = colorScheme.tertiary
            )
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            containerColor = colorScheme.surface
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(text = option.name,
                            style = MaterialTheme.typography.bodyLarge,
                            color = colorScheme.tertiary)
                    },
                    onClick = {
                        onOptionSelected(option)
                        isExpanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}



@Preview(showBackground = true, device = "spec:width=411dp,height=891dp,orientation=portrait")
@Composable
fun CreateEditGoalPreview() {
    ExnoiaAppTheme() {
        CreateEditGoalPortraitPreview()
    }
}