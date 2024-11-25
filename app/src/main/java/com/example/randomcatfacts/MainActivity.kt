package com.example.randomcatfacts

import androidx.compose.material.TopAppBar  // Material2 TopAppBar
import androidx.compose.material.Text      // Material2 Text
import androidx.compose.material3.Scaffold // Keep Scaffold from Material3, as it is stable in Material3
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold // Material3 Scaffold (still stable)
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar  // Material2 TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.randomcatfacts.ui.theme.RandomCatFactsTheme
import com.example.randomcatfacts.navigation.NavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            RandomCatFactsTheme {
                val navController = rememberNavController()
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("Random Cat Facts") })  // Material2 TopAppBar
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    // Pass the navController to the NavHost
                    NavGraph(navController = navController, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RandomCatFactsTheme {
        Greeting("Android")
    }
}
