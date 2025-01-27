package com.example.applications.model

enum class Priority(val value: Int) {
    NO_PRIORITY(0),
    LOW(1),
    MEDIUM(2),
    HIGH(3)
}

data class TaskModel(
    val id: String? = null,
    val title: String,
    val description: String,
    val priority: Int? = null
)






