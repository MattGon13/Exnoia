package dam_A51706.finalproject.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dam_A51706.finalproject.R
import dam_A51706.finalproject.ui.states.AuthUiState
import dam_A51706.finalproject.ui.theme.ExnoiaAppTheme
import dam_A51706.finalproject.viewmodel.AuthViewModel

@Composable
fun RegisterScreenPortrait(
    authViewModel: AuthViewModel = viewModel(),
    onRegisterSuccess: () -> Unit = {}
) {
    val uiState by authViewModel.uiState.collectAsState()

    LaunchedEffect(uiState) {
        if (uiState is AuthUiState.Success) {
            authViewModel.resetState()
            onRegisterSuccess()
        }
    }

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

            RegisterCard(authViewModel)

            if (uiState is AuthUiState.Loading) {
                Spacer(modifier = Modifier.height(20.dp))
                CircularProgressIndicator(color = colorScheme.primary)
            }
            if (uiState is AuthUiState.Error) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = (uiState as AuthUiState.Error).message,
                    color = colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun RegisterCard(
    authViewModel: AuthViewModel,
){
    val formState by authViewModel.formState.collectAsState()
    val uiState by authViewModel.uiState.collectAsState()

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
                value = formState.email,
                singleLine = true,
                shape = shapes.large,
                onValueChange = { authViewModel.updateEmail(it) },
                label = { Text("Email", color = colorScheme.onTertiary,
                    style = MaterialTheme.typography.labelSmall) },
                modifier = Modifier.padding(horizontal = 5.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorScheme.onSecondary,
                    unfocusedBorderColor = colorScheme.secondary,
                    unfocusedTextColor = colorScheme.onSecondary,
                    focusedTextColor = colorScheme.onSecondary,
                    cursorColor = colorScheme.onSecondary,
                    unfocusedContainerColor = colorScheme.secondary,
                    focusedContainerColor = colorScheme.secondary
                ),
            )

            OutlinedTextField(
                value = formState.password,
                singleLine = true,
                shape = shapes.large,
                onValueChange = { authViewModel.updatePassword(it) },
                label = { Text("Password", color = colorScheme.onTertiary,
                    style = MaterialTheme.typography.labelSmall) },
                modifier = Modifier.padding(horizontal = 5.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorScheme.onSecondary,
                    unfocusedBorderColor = colorScheme.secondary,
                    unfocusedTextColor = colorScheme.onSecondary,
                    focusedTextColor = colorScheme.onSecondary,
                    cursorColor = colorScheme.onSecondary,
                    unfocusedContainerColor = colorScheme.secondary,
                    focusedContainerColor = colorScheme.secondary
                ),
            )
            Button(
                onClick = { authViewModel.register() },
                enabled = uiState !is AuthUiState.Loading,
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

@Composable
fun RegisterScreenPortraitPreview() {
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

            RegisterCardPreview()
        }
    }
}

@Composable
fun RegisterCardPreview(){
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
                label = { Text("Email", color = colorScheme.onTertiary,
                    style = MaterialTheme.typography.labelSmall) },
                modifier = Modifier.padding(horizontal = 5.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorScheme.onSecondary,
                    unfocusedBorderColor = colorScheme.secondary,
                    unfocusedTextColor = colorScheme.onSecondary,
                    focusedTextColor = colorScheme.onSecondary,
                    cursorColor = colorScheme.onSecondary,
                    unfocusedContainerColor = colorScheme.secondary,
                    focusedContainerColor = colorScheme.secondary
                ),
            )

            OutlinedTextField(
                value = "password",
                singleLine = true,
                shape = shapes.large,
                onValueChange = {},
                label = { Text("Password", color = colorScheme.onTertiary,
                    style = MaterialTheme.typography.labelSmall) },
                modifier = Modifier.padding(horizontal = 5.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorScheme.onSecondary,
                    unfocusedBorderColor = colorScheme.secondary,
                    unfocusedTextColor = colorScheme.onSecondary,
                    focusedTextColor = colorScheme.onSecondary,
                    cursorColor = colorScheme.onSecondary,
                    unfocusedContainerColor = colorScheme.secondary,
                    focusedContainerColor = colorScheme.secondary
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
        RegisterScreenPortraitPreview()
    }
}