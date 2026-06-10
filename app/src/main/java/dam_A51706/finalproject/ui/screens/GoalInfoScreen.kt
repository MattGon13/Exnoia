package dam_A51706.finalproject.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dam_A51706.finalproject.R
import dam_A51706.finalproject.ui.theme.ExnoiaAppTheme
import dam_A51706.finalproject.viewmodel.GoalViewModel

@Composable
fun GoalInfoPortrait(
    goalViewModel: GoalViewModel,
    onBack: () -> Unit,
    onNavigateToEditGoal: () -> Unit
) {
    Scaffold(
    ) { padding ->
        Surface(color = MaterialTheme.colorScheme.surface) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp, end = 5.dp)
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
                            Icons.Default.Edit,
                            contentDescription = stringResource(R.string.edit),
                            tint = colorScheme.tertiary,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    Text("Correr a maratona",
                        color = colorScheme.primary,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .align(Alignment.Start)
                    )
                    Text("Correr a maratona de 50 km e ficar em primeiro",
                        color = colorScheme.tertiary,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .align(Alignment.Start)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    DetailInfoRow("Deadline:", "10/10/2026")
                    DetailInfoRow("Reward:", "Buy a new phone")
                    DetailInfoRow("Difficulty:", "Very hard")
                }

                Spacer(modifier = Modifier.height(10.dp))

                GoalSteps(listOf("Run 50 km", "Run 30 km"))
            }
        }
    }

}

@Composable
fun GoalInfoPortraitPreview() {
    Scaffold(
    ) { padding ->
        Surface(color = MaterialTheme.colorScheme.surface) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp, end = 5.dp)
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
                            Icons.Default.Edit,
                            contentDescription = stringResource(R.string.edit),
                            tint = colorScheme.tertiary,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    Text("Correr a maratona",
                        color = colorScheme.primary,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .align(Alignment.Start)
                    )
                    Text("Correr a maratona de 50 km e ficar em primeiro",
                        color = colorScheme.tertiary,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .align(Alignment.Start)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    DetailInfoRow("Deadline:", "10/10/2026")
                    DetailInfoRow("Reward:", "Buy a new phone")
                    DetailInfoRow("Difficulty:", "Very hard")
                }

                Spacer(modifier = Modifier.height(10.dp))

                GoalSteps(listOf("Run 50 km", "Run 30 km"))
            }
        }
    }

}

@Composable
fun GoalSteps(steps: List<String>){
    Column(
        modifier = Modifier
            .background(color = colorScheme.tertiary)
            .padding(top = 10.dp, bottom = 70.dp)
            .fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp, end = 5.dp)
        ){
            IconButton(
                onClick = {}
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_step),
                    tint = colorScheme.secondary,
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                )
            }
        }

        RecyclerViewSteps(steps)

    }
}


@Composable
fun RecyclerViewSteps(steps: List<String>){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .height(550.dp)
            .fillMaxWidth()
            .padding(start = 5.dp, end= 5.dp, top = 10.dp)
    ) {
        items(items = steps){
            StepRow(it, true)
        }
    }
}

@Composable
fun StepRow(title: String, done: Boolean) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 10.dp)
    ) {
        Checkbox(
            checked = done,
            onCheckedChange = { },
            colors = CheckboxDefaults.colors(
                uncheckedColor = colorScheme.onTertiary,
                checkedColor = colorScheme.primary,
                checkmarkColor = colorScheme.onTertiary,
            )
        )
        Text(
            title,
            color = colorScheme.onTertiary,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
fun DetailInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, color = colorScheme.secondary, style = MaterialTheme.typography.labelMedium)
        Text(value, color = colorScheme.tertiary, style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp,orientation=portrait")
@Composable
fun GoalInfoPreview() {
    ExnoiaAppTheme() {
        GoalInfoPortraitPreview()
    }
}