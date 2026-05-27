package dam_A51706.finalproject.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dam_A51706.finalproject.R
import dam_A51706.finalproject.ui.theme.ExnoiaAppTheme

@Composable
fun StatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, color = MaterialTheme.colorScheme.tertiary, style = MaterialTheme.typography.labelLarge, modifier = Modifier.padding(5.dp))
        Text(value, color = MaterialTheme.colorScheme.secondary, style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
fun MonthStats(completedGoals: Int, steps: Int){
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 25.dp, vertical = 30.dp)) {
            Text(
                stringResource(R.string.this_month),
                color = MaterialTheme.colorScheme.onTertiary,
                style = MaterialTheme.typography.labelLarge,
            )
            Spacer(modifier = Modifier.height(2.dp).background(color = MaterialTheme.colorScheme.onTertiary).fillMaxWidth())
            Spacer(modifier = Modifier.height(16.dp))
            MonthlyStatRow("Metas concluídas:", "2")
            Spacer(modifier = Modifier.height(10.dp))
            MonthlyStatRow("Passos realizados:", "7")
        }
    }
}

@Composable
fun MonthlyStatRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = MaterialTheme.colorScheme.secondary)
        Text(value, color = MaterialTheme.colorScheme.onTertiary, style = MaterialTheme.typography.labelMedium)
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp,orientation=portrait")
@Composable
fun StatsPreview() {
    ExnoiaAppTheme() {
        //StatItem("Metas", "11/20")
        MonthStats(2, 7)
    }
}