package dam_A51706.finalproject.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dam_A51706.finalproject.R
import dam_A51706.finalproject.ui.theme.ExnoiaAppTheme
import dam_A51706.finalproject.viewmodel.AuthViewModel
import dam_A51706.finalproject.viewmodel.GoalViewModel

@Composable
fun MainScreenPortrait(
    goalViewModel: GoalViewModel = viewModel(),
    authViewModel: AuthViewModel = viewModel(),
    onLogout: () -> Unit = {}
) {
    val totalGoalsCount by goalViewModel.totalGoalsCount.collectAsState()
    val totalGoalsCompleted by goalViewModel.totalGoalsCompleted.collectAsState()
    val totalStepsCount by goalViewModel.totalStepsCount.collectAsState()
    val totalStepsCompleted by goalViewModel.totalStepsCompleted.collectAsState()
    val goalsCompletedThisMonth by goalViewModel.goalsCompletedThisMonth.collectAsState()
    val stepsCompletedThisMonth by goalViewModel.stepsCompletedThisMonth.collectAsState()

    goalViewModel.loadGoals()

    Scaffold(
        bottomBar = { NavigationBar() },
    ) { padding ->
        Surface(color = MaterialTheme.colorScheme.surface) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp, vertical = 10.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    IconButton(onClick = {
                        authViewModel.signOut()
                        onLogout()
                    }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = stringResource(R.string.logout),
                            tint = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
                Image(
                    painter = painterResource(R.drawable.icone_estrela),
                    contentDescription = stringResource(R.string.star_icon),
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 30.dp)
                        .size(90.dp)
                )
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    StatItem(stringResource(R.string.goals), "$totalGoalsCompleted/$totalGoalsCount")
                    StatItem(stringResource(R.string.steps), "$totalStepsCompleted/$totalStepsCount")
                }

                Spacer(modifier = Modifier.height(50.dp))

                MonthStats(goalsCompletedThisMonth, stepsCompletedThisMonth)
            }
        }
    }

}

@Composable
fun MainScreenPortraitPreview() {
    Scaffold(
        bottomBar = { NavigationBar() },
    ) { padding ->
        Surface(color = MaterialTheme.colorScheme.surface) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp, vertical = 10.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.icone_estrela),
                    contentDescription = stringResource(R.string.star_icon),
                    modifier = Modifier
                        .padding(top = 50.dp, bottom = 30.dp)
                        .size(90.dp)
                )
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    StatItem("Metas", "11/20")
                    StatItem("Passos", "15/30")
                }

                Spacer(modifier = Modifier.height(50.dp))

                MonthStats(2, 7)
            }

        }
    }

}


@Preview(showBackground = true, device = "spec:width=411dp,height=891dp,orientation=portrait")
@Composable
fun MainScreenPreview() {
    ExnoiaAppTheme() {
        MainScreenPortraitPreview()
    }
}