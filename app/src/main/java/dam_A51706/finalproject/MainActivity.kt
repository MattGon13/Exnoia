package dam_A51706.finalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dam_A51706.finalproject.ui.theme.ExnoiaAppTheme
import com.google.firebase.auth.FirebaseAuth
import dam_A51706.finalproject.ui.navigation.AppNavGraph
import dam_A51706.finalproject.ui.navigation.Screen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExnoiaAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val auth = FirebaseAuth.getInstance()
                    val startDest = if (auth.currentUser != null) {
                        Screen.Main.route
                    } else {
                        Screen.Login.route
                    }
                    AppNavGraph(startDestination = startDest)
                }
            }
        }
    }
}