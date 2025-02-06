package com.example.applications


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.applications.ui.theme.ApplicationsTheme
import com.example.applications.view.TopStoryScreen
import com.example.applications.viewmodel.TopStoryViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationsTheme {
                val topStoryViewModel by viewModels<TopStoryViewModel>()
                TopStoryScreen(topStoryViewModel)
            }
        }
    }
}
