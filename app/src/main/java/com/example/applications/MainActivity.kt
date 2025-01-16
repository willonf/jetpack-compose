package com.example.applications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.applications.ui.theme.ApplicationsTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationsTheme {
                ExemploSnackBar()
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun ExemploText(nome: String) {
    Text(text = "Olá! Tudo bem, $nome?")
}

@Composable
fun ExemploButton() {
    Button(onClick = {}) {
        Text(text = "Login", fontSize = 20.sp)
    }
}

@Composable
fun ExemploTextField() {
    var texto by remember { mutableStateOf("") }

    TextField(
        value = texto,
        onValueChange = { texto = it },
        label = { Text(text = "Digite aqui") }
    )
}

@Composable
fun ExemploCard() {
    Card(
        modifier = Modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Título do Card", style = MaterialTheme.typography.headlineMedium)
            Text(text = "Conteúdo do Card")
        }
    }
}

@Composable
fun ExemploColumn() {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(text = "Topo")
        Text(text = "Centro")
        Text(text = "Base")
    }
}

@Composable
fun Calculadora(
    modifier: Modifier = Modifier,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState
) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {

        var valueA by remember { mutableStateOf("") }
        var valueB by remember { mutableStateOf("") }
        var number1 by remember { mutableStateOf("") }
        var number2 by remember { mutableStateOf("") }
        var expression by remember { mutableStateOf("") }

        Text(text = "Valor de A: ")
        TextField(
            value = valueA,
            onValueChange = {
                valueA = it
                number1 = valueA
            },
            modifier = modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )

        Text(text = "Valor de B: ")
        TextField(
            value = valueB,
            onValueChange = {
                valueB = it
                number2 = valueB
            },
            modifier = modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )

        Text(text = expression)

        Button(
            onClick = {
                val result = valueA.toInt() + valueB.toInt()
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "O resultado é $result",
                        duration = SnackbarDuration.Short
                    )
                }
            }, modifier = modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Calcular")
        }
    }
}


@Composable
fun ExemploLazyColumn() {
    val listaDeOpcoes = listOf("Item 1", "Item 2", "Item 3", "Item 4")

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(listaDeOpcoes, key = { it }) {
            Text(text = it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExemploTopAppBar() {
    TopAppBar(
        title = { Text("Minha AppBar") },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Search, contentDescription = "Buscar")
            }
            IconButton(onClick = {}) {
                Icon(Icons.Default.AccountBox, contentDescription = "Perfil")
            }
        }
    )
}

@Composable
fun ExemploBottomAppBar() {
    BottomAppBar(
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Search, contentDescription = "Buscar")
            }
            IconButton(onClick = {}) {
                Icon(Icons.Default.AccountBox, contentDescription = "Perfil")
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(Icons.Outlined.Add, contentDescription = null)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExemploScaffold() {
    var presses by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("TÍTULO DA APP BAR") })
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                Text(
                    text = "Bottom app bar",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { presses++ }) {
                Icon(Icons.Default.Add, contentDescription = "")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = """
                    Esse é um exemplo de scaffold!
                    
                    Você pressionou o botão $presses vezes
                """.trimIndent()
            )

        }
    }

}

@Composable
fun ExemploSnackBar() {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Show snackbar") },
                icon = { Icon(Icons.Default.Info, contentDescription = "") },
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar("Snackbar exibida")
                    }
                }
            )
        }
    ) { contentPadding ->
        Text(
            text = "Olá!",
            modifier = Modifier
                .padding(contentPadding)
                .padding(10.dp)
        )
    }
}


@Preview()
@Composable
fun GreetingPreview() {
    ApplicationsTheme {
        ExemploSnackBar()
    }
}