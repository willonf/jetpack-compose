package com.example.applications.serializer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ApiResponseSerializer<T>(
    val status: String = "",
    val copyright: String = "",
    val section: String = "",
    @SerialName("last_update") val lastUpdate: String = "",
    @SerialName("num_results") val numResults: Int = 0,
    val results: List<T> = emptyList()
)