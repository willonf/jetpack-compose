package com.example.applications


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.applications.datasource.PreferencesDataStore
import com.example.applications.ui.theme.ApplicationsTheme
import com.example.applications.view.SaveTask
import com.example.applications.view.TaskList
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

object PreferencesKeys {
    val IS_DARK_MODE = booleanPreferencesKey("darkMode")
    val CLICKS = intPreferencesKey("clicks")
}

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val clicksFlow = remember {
                PreferencesDataStore(this).getPreference(
                    PreferencesKeys.CLICKS
                )
            }
            val clicks by clicksFlow.collectAsState(initial = 0)

//            var clicks by remember { mutableIntStateOf(0) }

            ApplicationsTheme() {
                val context = LocalContext.current
                val scope = rememberCoroutineScope()
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    "Contador de cliques",
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                            },
                        )
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(text = "Cliques: ${clicks ?: 0}", fontSize = 25.sp)

                        Button(onClick = {
                            scope.launch {
                                PreferencesDataStore(context).setPreference( PreferencesKeys.CLICKS, clicks?.plus(1) ?: 0)
                            }
                        }) {
                            Text("Clique aqui")
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun MainPreview() {
    ApplicationsTheme() {


    }
}