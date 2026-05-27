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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dam_A51706.finalproject.R
import dam_A51706.finalproject.ui.theme.ExnoiaAppTheme

@Composable
fun RegisterScreenPortrait() {
    Surface(color = colorScheme.surface) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
        ) {

            Spacer(modifier = Modifier.height(150.dp))

            Text(
                stringResource(R.string.join_us),
                color = colorScheme.secondary,
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(10.dp))

            RegisterCard()

            Spacer(modifier = Modifier.height(50.dp))

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ){
                Image(
                    painter = painterResource(R.drawable.simbolo_extended),
                    contentDescription = stringResource(R.string.star_icon),
                    modifier = Modifier
                        .size(250.dp)
                        .graphicsLayer()
                        .padding(start = 100.dp)
                )
            }
        }
    }
}

@Composable
fun RegisterCard(){
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = colorScheme.tertiary),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 25.dp, vertical = 30.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = "email",
                singleLine = true,
                shape = shapes.large,
                onValueChange = {},
                modifier = Modifier.padding(horizontal = 5.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorScheme.onSecondary,
                    unfocusedBorderColor = colorScheme.secondary,
                    unfocusedTextColor = colorScheme.onSecondary,
                    cursorColor = colorScheme.onSecondary,
                    unfocusedContainerColor = colorScheme.secondary
                ),
            )

            OutlinedTextField(
                value = "password",
                singleLine = true,
                shape = shapes.large,
                onValueChange = {},
                modifier = Modifier.padding(horizontal = 5.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorScheme.secondary,
                    focusedLabelColor = colorScheme.secondary,
                    unfocusedBorderColor = colorScheme.secondary,
                    unfocusedLabelColor = colorScheme.onSecondary,
                    unfocusedTextColor = colorScheme.onPrimary,
                    cursorColor = colorScheme.onPrimary,
                    unfocusedContainerColor = colorScheme.secondary
                ),
            )
            Button(
                onClick = {},
                modifier = Modifier
                    .width(150.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorScheme.primary,
                    contentColor = colorScheme.onPrimary
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp)
            ) {
                Text(
                    text = stringResource(R.string.sign_up),
                    style = MaterialTheme.typography.labelLarge
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.don_t_have_an_account),
                    style = MaterialTheme.typography.bodyLarge
                )
                TextButton(
                    onClick = {},
                    modifier = Modifier.height(50.dp)
                ) {
                    Text(
                        text = stringResource(R.string.login),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(0.dp)
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp,orientation=portrait")
@Composable
fun RegisterPreview() {
    ExnoiaAppTheme() {
        RegisterScreenPortrait()
    }
}