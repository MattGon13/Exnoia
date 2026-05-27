package dam_A51706.finalproject.ui.screens

import android.graphics.Paint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dam_A51706.finalproject.R
import dam_A51706.finalproject.ui.theme.appTypography

@Composable
fun GoalsPortrait() {
    Scaffold(
        bottomBar = { NavigationBar() },
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
                            Icon(
                                Icons.Default.Search,
                                contentDescription = stringResource(R.string.search_icon),
                                tint = colorScheme.onTertiary )
                        },
                        onValueChange = {},
                        modifier = Modifier.padding(horizontal = 5.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = colorScheme.onSecondary,
                            unfocusedBorderColor = colorScheme.secondary,
                            unfocusedTextColor = colorScheme.onSecondary,
                            cursorColor = colorScheme.onSecondary,
                            unfocusedContainerColor = colorScheme.tertiary
                        ),
                    )
                }
                Spacer(modifier = Modifier.height(50.dp))
                RecyclerView(listOf("Correr a maratona", "Aprender italiano"))
            }
        }
    }
}

@Composable
fun TitleCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(MaterialTheme.colorScheme.tertiary)
            .clip(RectangleShape),

    ) {
        Image(
            painter = painterResource(R.drawable.padrao2),
            contentDescription = stringResource(R.string.card_pattern),
            modifier = Modifier
                .align(Alignment.Center)
                .graphicsLayer(scaleX = 2.8f, scaleY = 2.8f)
        )
        IconButton(
            onClick = {},
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = stringResource(R.string.go_back),
                tint = colorScheme.onTertiary,
                modifier = Modifier.height(50.dp).width(50.dp)
            )
        }
        Text(
            "Goals", color = colorScheme.onTertiary,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}


@Composable
fun GoalCard(goalTitle: String) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 5.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        onClick = {},
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.secondary
        )
    ) {
        Row(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, start = 15.dp)) {
            Text(
                text = goalTitle,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(10.dp, 20.dp)
            )
        }
    }
}

@Composable
fun RecyclerView(goals: List<String>){
    LazyColumn() {
        items(items = goals){
            GoalCard(it)
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp,orientation=portrait")
@Composable
fun GoalsPreview() {
    _root_ide_package_.dam_A51706.finalproject.ui.theme.ExnoiaAppTheme() {
        GoalsPortrait()
    }
}