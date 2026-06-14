package dam_A51706.finalproject.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dam_A51706.finalproject.R
import dam_A51706.finalproject.data.model.Goal
import dam_A51706.finalproject.viewmodel.GoalViewModel

@Composable
fun GoalsPortrait(
    goalViewModel: GoalViewModel = viewModel(),
    onNavigateToMain: () -> Unit = {},
    onNavigateToGoalInfo: (String) -> Unit = {},
    onNavigateToCreateGoal: () -> Unit = {}
) {
    val searchQuery by goalViewModel.searchQuery.collectAsState()
    val filteredGoals by goalViewModel.filteredGoals.collectAsState()
    val uiState by goalViewModel.uiState.collectAsState()

    goalViewModel.loadGoals()

    Scaffold(
        bottomBar = {
            NavigationBar(
                currentRoute = "goals",
                onNavigateToMain = onNavigateToMain
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToCreateGoal,
                containerColor = colorScheme.tertiary,
                contentColor = colorScheme.onTertiary
            ) {
                Icon(Icons.Filled.Add, stringResource(R.string.add_goal))
            }
        }
    ) { padding ->
        Surface(color = MaterialTheme.colorScheme.surface) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                TitleCard()
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = colorScheme.secondary)
                        .padding(top = 10.dp, bottom = 10.dp)
                ){
                    OutlinedTextField(
                        value = searchQuery,
                        singleLine = true,
                        shape = RoundedCornerShape(25.dp),
                        leadingIcon = {
                            IconButton(
                                onClick = {
                                    goalViewModel.updateSearchQuery(searchQuery)
                                }
                            ) {
                                Icon(
                                    Icons.Default.Search,
                                    contentDescription = stringResource(R.string.search_icon),
                                    tint = colorScheme.onTertiary,
                                    modifier = Modifier
                                        .height(30.dp)
                                        .width(30.dp)
                                )
                            }
                        },
                        onValueChange = { goalViewModel.updateSearchQuery(it) },
                        modifier = Modifier.padding(horizontal = 5.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = colorScheme.onSecondary,
                            unfocusedBorderColor = colorScheme.secondary,
                            unfocusedTextColor = colorScheme.onSecondary,
                            focusedTextColor = colorScheme.onSecondary,
                            cursorColor = colorScheme.onSecondary,
                            unfocusedContainerColor = colorScheme.tertiary,
                            focusedContainerColor = colorScheme.tertiary
                        ),
                        placeholder = { Text(stringResource(R.string.search), color = colorScheme.onTertiary) }
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp)
                ) {
                    if(searchQuery.isNotBlank()){
                        items(filteredGoals, key = { it.id }) { goal ->
                            GoalCard(
                                goal = goal,
                                onClick = {
                                    goalViewModel.selectGoal(goal)
                                    onNavigateToGoalInfo(goal.id)
                                }
                            )
                        }
                    }
                    else{
                        items(uiState.goals, key = { it.id }) { goal ->
                            GoalCard(
                                goal = goal,
                                onClick = {
                                    goalViewModel.selectGoal(goal)
                                    onNavigateToGoalInfo(goal.id)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GoalsPortraitPreview() {
    Scaffold(
        bottomBar = { NavigationBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = colorScheme.tertiary,
                contentColor = colorScheme.onTertiary
            ) {
                Icon(Icons.Filled.Add, stringResource(R.string.add_goal))
            }
        }
    ) { padding ->
        Surface(color = colorScheme.surface) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                TitleCard()
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = colorScheme.secondary)
                        .padding(top = 10.dp, bottom = 10.dp)
                ){
                    OutlinedTextField(
                        value = "",
                        singleLine = true,
                        shape = RoundedCornerShape(25.dp),
                        leadingIcon = {
                            IconButton(
                                onClick = { }
                            ) {
                                Icon(
                                    Icons.Default.Search,
                                    contentDescription = stringResource(R.string.search_icon),
                                    tint = colorScheme.onTertiary,
                                    modifier = Modifier
                                        .height(30.dp)
                                        .width(30.dp)
                                )
                            }
                        },
                        onValueChange = {},
                        modifier = Modifier.padding(horizontal = 5.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = colorScheme.onSecondary,
                            unfocusedBorderColor = colorScheme.secondary,
                            unfocusedTextColor = colorScheme.onSecondary,
                            focusedTextColor = colorScheme.onSecondary,
                            cursorColor = colorScheme.onSecondary,
                            unfocusedContainerColor = colorScheme.tertiary,
                            focusedContainerColor = colorScheme.tertiary
                        ),
                    )
                }
                Spacer(modifier = Modifier.height(50.dp))
                RecyclerViewGoals(listOf("Correr a maratona", "Aprender italiano"))
            }
        }
    }
}

@Composable
fun TitleCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .background(MaterialTheme.colorScheme.tertiary)
            .clip(RectangleShape),

        ) {
        Image(
            painter = painterResource(R.drawable.padrao2),
            contentDescription = stringResource(R.string.card_pattern),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.Center)
                .size(800.dp)
                .scale(1.3f)
        )
        Text(
            "Goals", color = colorScheme.onTertiary,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun GoalCard(goal: Goal, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 5.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.secondary
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(top = 5.dp, bottom = 5.dp, start = 15.dp, end = 15.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = goal.title,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(10.dp, 20.dp).weight(1f)
            )
            if (goal.isComplete) {
                Icon(
                    Icons.Filled.CheckCircle,
                    contentDescription = stringResource(R.string.check),
                    tint = colorScheme.tertiary,
                    modifier = Modifier.size(30.dp)
                )
            } else {
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = stringResource(R.string.go_back),
                    tint = colorScheme.tertiary,
                    modifier = Modifier.height(50.dp).width(50.dp)
                )
            }
        }
    }
}


@Composable
fun GoalCardPreview(goalTitle: String) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 5.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        onClick = {},
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.secondary
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(top = 5.dp, bottom = 5.dp, start = 15.dp, end = 15.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = goalTitle,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(10.dp, 20.dp)
            )
            IconButton(
                onClick = {}
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = stringResource(R.string.go_back),
                    tint = colorScheme.tertiary,
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                )
            }
        }
    }
}

@Composable
fun RecyclerViewGoals(goals: List<String>){
    LazyColumn(
        modifier = Modifier.height(480.dp)
    ) {
        items(items = goals){
            GoalCardPreview(it)
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp,orientation=portrait")
@Composable
fun GoalsPreview() {
    _root_ide_package_.dam_A51706.finalproject.ui.theme.ExnoiaAppTheme() {
        GoalsPortraitPreview()
    }
}