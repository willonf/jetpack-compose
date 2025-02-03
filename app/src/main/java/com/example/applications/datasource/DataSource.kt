package com.example.applications.datasource

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.Preferences.Key
import androidx.datastore.preferences.core.edit
import com.example.applications.dataStore
import com.example.applications.model.TaskModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


class PreferencesDataStore(private val context: Context) {

    fun <T> getPreference(key: Key<T>): Flow<T?> {
        return context.dataStore.data.map { preferences -> preferences[key] }
    }

    suspend fun <T> setPreference(key: Key<T>, value: T) {
        context.dataStore.edit { preferences -> preferences[key] = value }
    }
}

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







