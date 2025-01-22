package com.example.applications.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.applications.components.CustomButtom
import com.example.applications.components.MyTextField
import com.example.applications.ui.theme.GreenRadioButtonDisabled
import com.example.applications.ui.theme.GreenRadioButtonSelected
import com.example.applications.ui.theme.PurpleGrey40
import com.example.applications.ui.theme.RedRadioButtonDisabled
import com.example.applications.ui.theme.RedRadioButtonSelected
import com.example.applications.ui.theme.YellowRadioButtonDisabled
import com.example.applications.ui.theme.YellowRadioButtonSelected


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaveTask(navController: NavController) {

    var taskTitle by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var noPriority by remember { mutableStateOf(false) }
    var lowPriority by remember { mutableStateOf(false) }
    var mediumPriority by remember { mutableStateOf(false) }
    var highPriority by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Salvar tarefa",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                colors = topAppBarColors(containerColor = PurpleGrey40)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            MyTextField(
                value = taskTitle,
                onValueChange = {
                    taskTitle = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 20.dp, 20.dp, 0.dp),
                label = "Título da tarefa",
                maxLines = 1,
                keyboardType = KeyboardType.Text,
            )

            MyTextField(
                value = taskDescription,
                onValueChange = {
                    taskDescription = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(20.dp, 20.dp, 20.dp, 0.dp),
                label = "Descrição",
                maxLines = 5,
                keyboardType = KeyboardType.Text,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Nível de prioridade")
                RadioButton(
                    selected = lowPriority,
                    onClick = {
                        lowPriority = !lowPriority
                        mediumPriority = false
                        highPriority = false
                    },
                    colors = RadioButtonColors(
                        unselectedColor = GreenRadioButtonDisabled,
                        selectedColor = GreenRadioButtonSelected,
                        disabledSelectedColor = Color.Unspecified,
                        disabledUnselectedColor = Color.Unspecified
                    )
                )
                RadioButton(
                    selected = mediumPriority,
                    onClick = {
                        mediumPriority = !mediumPriority
                        lowPriority = false
                        highPriority = false

                    },
                    colors = RadioButtonColors(
                        unselectedColor = YellowRadioButtonDisabled,
                        selectedColor = YellowRadioButtonSelected,
                        disabledSelectedColor = Color.Unspecified,
                        disabledUnselectedColor = Color.Unspecified
                    )
                )
                RadioButton(
                    selected = highPriority,
                    onClick = {
                        highPriority = !highPriority
                        lowPriority = false
                        mediumPriority = false
                    },
                    colors = RadioButtonColors(
                        unselectedColor = RedRadioButtonDisabled,
                        selectedColor = RedRadioButtonSelected,
                        disabledSelectedColor = Color.Unspecified,
                        disabledUnselectedColor = Color.Unspecified
                    )
                )
            }
            CustomButtom(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(10.dp),
                label = "Salvar"
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun SaveTaskPreview() {
    var taskTitle by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var noPriority by remember { mutableStateOf(false) }
    var lowPriority by remember { mutableStateOf(false) }
    var mediumPriority by remember { mutableStateOf(false) }
    var highPriority by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Salvar tarefa",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                colors = topAppBarColors(
                    containerColor = PurpleGrey40,
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            MyTextField(
                value = taskTitle,
                onValueChange = {
                    taskTitle = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 20.dp, 20.dp, 0.dp),
                label = "Título da tarefa",
                maxLines = 1,
                keyboardType = KeyboardType.Text,
            )

            MyTextField(
                value = taskDescription,
                onValueChange = {
                    taskDescription = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(20.dp, 20.dp, 20.dp, 0.dp),
                label = "Descrição",
                maxLines = 5,
                keyboardType = KeyboardType.Text,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Nível de prioridade")
                RadioButton(
                    selected = lowPriority,
                    onClick = {
                        lowPriority = !lowPriority
                        mediumPriority = false
                        highPriority = false
                    },
                    colors = RadioButtonColors(
                        unselectedColor = GreenRadioButtonDisabled,
                        selectedColor = GreenRadioButtonSelected,
                        disabledSelectedColor = Color.Unspecified,
                        disabledUnselectedColor = Color.Unspecified
                    )
                )
                RadioButton(
                    selected = mediumPriority,
                    onClick = {
                        mediumPriority = !mediumPriority
                        lowPriority = false
                        highPriority = false

                    },
                    colors = RadioButtonColors(
                        unselectedColor = YellowRadioButtonDisabled,
                        selectedColor = YellowRadioButtonSelected,
                        disabledSelectedColor = Color.Unspecified,
                        disabledUnselectedColor = Color.Unspecified
                    )
                )
                RadioButton(
                    selected = highPriority,
                    onClick = {
                        highPriority = !highPriority
                        lowPriority = false
                        mediumPriority = false
                    },
                    colors = RadioButtonColors(
                        unselectedColor = RedRadioButtonDisabled,
                        selectedColor = RedRadioButtonSelected,
                        disabledSelectedColor = Color.Unspecified,
                        disabledUnselectedColor = Color.Unspecified
                    )
                )
            }
            CustomButtom(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(10.dp),
                label = "Salvar"
            )
        }
    }
}