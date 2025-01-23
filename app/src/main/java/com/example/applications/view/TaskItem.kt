package com.example.applications.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.applications.R
import com.example.applications.model.TaskModel
import com.example.applications.ui.theme.ShapePriorityCard

@Composable
fun TaskItem(
    task: TaskModel
) {

    val taskTitle = task.title
    val taskDescription = task.description
    val taskPriority = task.priority
    val priorityLevel: String = when (taskPriority) {
        0 -> "Sem prioridade"
        1 -> "Prioridade Baixa"
        2 -> "Prioridade Média"
        else -> "Prioridade Alta"
    }

    val priorityColor: Color = when (taskPriority) {
        0 -> Color.Black
        1 -> Color.Green
        2 -> Color.Yellow
        else -> Color.Red
    }


    Card(
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.Unspecified,
            disabledContainerColor = Color.Unspecified,
            disabledContentColor = Color.Unspecified
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(20.dp).fillMaxWidth()
        ) {
            val (txtTaskTitle, txtTaskDescription,
                txtTaskPriority, cardTaskPriorityColor,
                btnDelete) = createRefs()

            Text(text = taskTitle!!,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(txtTaskTitle) {
                    top.linkTo(  // Em relação ao componente pai (ConstraintLayout)
                        parent.top,
                        margin = 10.dp
                    )
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )

            Text(text = taskDescription!!,
                modifier = Modifier.constrainAs(txtTaskDescription) {
                    top.linkTo(
                        txtTaskTitle.bottom, // Em relação ao componente de título
                        margin = 10.dp
                    )
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )
            Text(text = priorityLevel,
                modifier = Modifier.constrainAs(txtTaskPriority) {
                    top.linkTo(
                        txtTaskDescription.bottom, // Em relação ao componente de descrição
                        margin = 10.dp
                    )
                    start.linkTo(parent.start, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                }
            )

            Card(
                shape = ShapePriorityCard.large,
                colors = CardDefaults.cardColors(
                    containerColor = priorityColor,
                ),
                modifier = Modifier
                    .size(30.dp)
                    .constrainAs(cardTaskPriorityColor) {
                        top.linkTo(txtTaskDescription.bottom, margin = 10.dp)
                        start.linkTo(txtTaskPriority.end, margin = 10.dp)
                        bottom.linkTo(parent.bottom, margin = 10.dp)
                    }
            ) { }

            IconButton(
                onClick = {},
                modifier = Modifier.constrainAs(btnDelete) {
                    top.linkTo(txtTaskDescription.bottom, margin = 10.dp)
                    start.linkTo(txtTaskPriority.end, margin = 30.dp)
                    end.linkTo(parent.end, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                }
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_delete),
                    contentDescription = ""
                )
            }

        }
    }
}

