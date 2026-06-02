package dam_A51706.finalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dam_A51706.finalproject.ui.screens.CreateEditGoalPortrait
import dam_A51706.finalproject.ui.theme.ExnoiaAppTheme

class CreateEditGoalActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExnoiaAppTheme() {
                CreateEditGoalPortrait()
            }
        }
    }
}