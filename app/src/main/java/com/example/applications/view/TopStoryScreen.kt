package com.example.applications.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.applications.viewmodel.TopStoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopStoryScreen(viewModel: TopStoryViewModel) {

    val topStories by viewModel.departmentList.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Notícias",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                    )
                },
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            if (topStories.results.isNotEmpty()) {
                TopStoryList(topStories.results)
            }
        }
    }
}