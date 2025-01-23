package com.example.applications.datasource

import android.util.Log
import com.example.applications.model.TaskModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class DataSource {

    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val collectionReference = firestore.collection("tarefas")
    private val now = Clock.System.now().toLocalDateTime(TimeZone.UTC)

    fun saveTask(taskModel: TaskModel) {
        val documentReference = collectionReference.document()
        val taskPayload = mapOf(
            "id" to documentReference.id,
            "title" to taskModel.title,
            "description" to taskModel.description,
            "priority" to taskModel.priority,
            "createdAt" to now.date
        )
        documentReference
            .set(taskPayload)
            .addOnCompleteListener {
                Log.d("Firestore", "Document saved with ID: $now-${taskModel.title}")
            }
            .addOnFailureListener {
                Log.d("Firestore", "Error on save document with ID: $now-${taskModel.title}")
            }
            .addOnCanceledListener {
                Log.d("Firestore", "FINALIZED")
            }
    }

}







