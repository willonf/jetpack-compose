package com.example.applications.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.applications.PreferencesKeys
import com.example.applications.dataStore
import com.example.applications.datasource.PreferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskList(
    navController: NavController,
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val isDarkModeFlow = remember {
        context.dataStore.data
            .map { preferences ->
                preferences[PreferencesKeys.IS_DARK_MODE] ?: false
            }
    }
    val isDarkMode by isDarkModeFlow.collectAsState(initial = false)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Lista de tarefas",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                    )
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = Color.White,
                ),
                actions = {
                    Switch(
                        checked = isDarkMode,
                        onCheckedChange = { isChecked ->
                            scope.launch {
                                PreferencesDataStore(context).setPreference(PreferencesKeys.IS_DARK_MODE, isChecked)
                            }
                        }
                    )

                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("saveTask")
                },
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) {

    }
}

@Preview(showBackground = true)
@Composable
private fun TaskListPreview() {
    val navController = rememberNavController()
    TaskList(navController)
}

