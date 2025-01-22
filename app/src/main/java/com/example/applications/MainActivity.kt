package com.example.applications


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.applications.ui.theme.ApplicationsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationsTheme {
                ExemploScaffold()
            }
        }
    }
}

@Composable
fun CalculatorScreen() {
    var entrada by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var operadorEscolhido by remember { mutableStateOf<Char?>(null) }
    var valor1 by remember { mutableStateOf<Double?>(null) }
    var valor2 by remember { mutableStateOf<Double?>(null) }

    fun handleInput(label: String) {
        when (label) {
            "C" -> {
                entrada = ""
                result = ""
                operadorEscolhido = null
                valor1 = null
                valor2 = null
            }

            "+", "-", "×", "÷" -> {
                if (valor1 == null && entrada.isNotEmpty()) {
                    valor1 = entrada.toDoubleOrNull()
                    operadorEscolhido = label.first()
                    entrada = ""
                }
            }

            "=" -> {
                valor2 = entrada.toDoubleOrNull()
                if (valor2 != null) {
                    result = calcular(valor1!!, valor2!!, operadorEscolhido)
                    entrada = result
                    valor1 = null
                    valor2 = null
                    operadorEscolhido = null
                }
            }

            else -> entrada += label
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Exibição do cálculo e resultado
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = if (operadorEscolhido != null) "$valor1 $operadorEscolhido $entrada" else entrada,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.End,
                color = Color.Gray
            )
            Text(
                text = result,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.End
            )
        }

        // Botões da calculadora
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val botoes = listOf(
                listOf("C", "+/-", "%", "÷"),
                listOf("7", "8", "9", "×"),
                listOf("4", "5", "6", "-"),
                listOf("1", "2", "3", "+"),
                listOf("0", ".", "<", "=")
            )
            botoes.forEach { row ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    row.forEach { label ->
                        Button(
                            onClick = { handleInput(label) },
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f),
                        ) {
                            Text(
                                text = label,
                                fontSize = 20.sp,
                                color = if (label in listOf(
                                        "÷",
                                        "×",
                                        "-",
                                        "+",
                                        "=",
                                        "C",
                                        "%",
                                        "+/-"
                                    )
                                ) Color.White else Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}

fun calcular(operando1: Double, operando2: Double, operador: Char?): String {
    return when (operador) {
        '+' -> (operando1 + operando2).toString()
        '-' -> (operando1 - operando2).toString()
        '×' -> (operando1 * operando2).toString()
        '÷' -> if (operando2 != 0.0) (operando1 / operando2).toString() else "Erro"
        else -> "Erro"
    }
}

@Composable
fun ExemploColumn() {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Topo", fontSize = 40.sp)
        Text(text = "Centro", fontSize = 40.sp)
        Text(text = "Base", fontSize = 40.sp)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExemploScaffold() {
    var pressionado by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "TOP APP BAR") })
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                Text(
                    "BOTTOM APP BAR",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { pressionado++ }
            ) {
                Icon(Icons.Default.Add, contentDescription = "")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Você pressionou $pressionado vezes",
                modifier = Modifier.padding(8.dp),
                fontSize = 25.sp
            )
        }

    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ApplicationsTheme {
        ExemploScaffold()
    }
}