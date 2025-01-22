package com.example.applications


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.applications.ui.theme.ApplicationsTheme
import com.example.applications.view.SaveTask
import com.example.applications.view.TaskList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationsTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "taskList") {
                    composable(route = "taskList") { TaskList(navController) }
                    composable(route = "saveTask") { SaveTask(navController) }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ApplicationsTheme {
    }
}