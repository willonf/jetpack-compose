package com.example.applications.view

import android.content.Context
import android.widget.Toast
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.applications.components.CustomButtom
import com.example.applications.components.MyTextField
import com.example.applications.model.Priority
import com.example.applications.model.TaskModel
import com.example.applications.repository.TaskRepository
import com.example.applications.ui.theme.GreenRadioButtonSelected
import com.example.applications.ui.theme.GreenRadioButtonUnselected
import com.example.applications.ui.theme.PurpleGrey40
import com.example.applications.ui.theme.RedRadioButtonSelected
import com.example.applications.ui.theme.RedRadioButtonUnselected
import com.example.applications.ui.theme.YellowRadioButtonSelected
import com.example.applications.ui.theme.YellowRadioButtonUnselected
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


fun onClickSaveButton(
    scope: CoroutineScope, context: Context, taskModel: TaskModel
) {
    val taskRepository = TaskRepository()
    var isValid = true;

    scope.launch(Dispatchers.IO) {
        isValid = taskModel.title.isNotEmpty() && taskModel.description.isNotEmpty()
        taskRepository.saveTask(taskModel)
    }
    scope.launch(Dispatchers.Main) {
        if (isValid) {
            Toast.makeText(context, "Salvo com sucesso!", Toast.LENGTH_SHORT).show()
        } else {
            if (taskModel.title.isEmpty()) {
                Toast.makeText(context, "Título é obrigatório!", Toast.LENGTH_SHORT).show()
            } else if (taskModel.description.isEmpty()) {
                Toast.makeText(
                    context, "Descrição é obrigatória!", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaveTask(navController: NavController) {

    var taskTitle by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var taskPriority by remember { mutableIntStateOf(Priority.NO_PRIORITY.value) }
    var lowPriority by remember { mutableStateOf(false) }
    var mediumPriority by remember { mutableStateOf(false) }
    var highPriority by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current


    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "Salvar tarefa",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }, colors = topAppBarColors(containerColor = PurpleGrey40)
        )
    }) { innerPadding ->
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
                    selected = lowPriority, onClick = {
                        taskPriority = Priority.LOW.value
                        lowPriority = !lowPriority
                        mediumPriority = false
                        highPriority = false
                    }, colors = RadioButtonColors(
                        unselectedColor = GreenRadioButtonUnselected,
                        selectedColor = GreenRadioButtonSelected,
                        disabledSelectedColor = Color.Unspecified,
                        disabledUnselectedColor = Color.Unspecified
                    )
                )
                RadioButton(
                    selected = mediumPriority, onClick = {
                        taskPriority = Priority.MEDIUM.value
                        mediumPriority = !mediumPriority
                        lowPriority = false
                        highPriority = false

                    }, colors = RadioButtonColors(
                        unselectedColor = YellowRadioButtonUnselected,
                        selectedColor = YellowRadioButtonSelected,
                        disabledSelectedColor = Color.Unspecified,
                        disabledUnselectedColor = Color.Unspecified
                    )
                )
                RadioButton(
                    selected = highPriority, onClick = {
                        taskPriority = Priority.HIGH.value
                        highPriority = !highPriority
                        lowPriority = false
                        mediumPriority = false
                    }, colors = RadioButtonColors(
                        unselectedColor = RedRadioButtonUnselected,
                        selectedColor = RedRadioButtonSelected,
                        disabledSelectedColor = Color.Unspecified,
                        disabledUnselectedColor = Color.Unspecified
                    )
                )
            }

            CustomButtom(
                onClick = {
                    onClickSaveButton(
                        scope,
                        context,
                        TaskModel(
                            title = taskTitle,
                            description = taskDescription,
                            priority = taskPriority
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(10.dp),
                label = "Salvar",
                buttonColor = Color.Blue,
                contentColor = Color.White
            )
        }
    }
}
