package com.example.applications.repository

import com.example.applications.datasource.DataSource
import com.example.applications.model.TaskModel

class TaskRepository {
    private val dataSource: DataSource = DataSource()

    fun saveTask(taskModel: TaskModel) {
        dataSource.saveTask(taskModel)
    }
}










