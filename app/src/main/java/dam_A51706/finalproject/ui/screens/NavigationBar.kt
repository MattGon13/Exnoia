package dam_A51706.finalproject.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dam_A51706.finalproject.R
import dam_A51706.finalproject.ui.theme.ExnoiaAppTheme

@Composable
fun NavigationBar() {
    NavigationBar(containerColor = MaterialTheme.colorScheme.primary) {
        NavigationBarItem(
            selected = true,
            onClick = { },
            icon = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = stringResource(R.string.home),
                    tint = MaterialTheme.colorScheme.tertiary )
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = MaterialTheme.colorScheme.onPrimary // Highlight color for selected
            ),
            label = { Text(stringResource(R.string.home),  color = MaterialTheme.colorScheme.onPrimary) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                Icon(
                    Icons.Default.Star,
                    contentDescription = stringResource(R.string.goals),
                    tint = MaterialTheme.colorScheme.tertiary )
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = MaterialTheme.colorScheme.onPrimary // Highlight color for selected
            ),
            label = { Text(stringResource(R.string.goals), color = MaterialTheme.colorScheme.onPrimary) }
        )
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp,orientation=portrait")
@Composable
fun NavigationBarPreview() {
    ExnoiaAppTheme() {
        NavigationBar()
    }
}