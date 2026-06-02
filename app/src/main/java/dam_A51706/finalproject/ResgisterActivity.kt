package dam_A51706.finalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dam_A51706.finalproject.ui.screens.RegisterScreenPortrait
import dam_A51706.finalproject.ui.theme.ExnoiaAppTheme

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExnoiaAppTheme() {
                RegisterScreenPortrait()
            }
        }
    }
}