package com.example.applications.repository

import android.util.Log
import com.example.applications.serializer.ApiResponseSerializer
import com.example.applications.serializer.TopStorySerializer
import com.example.applications.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class TopStoryRepository {
    private val httpClient = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    suspend fun getAll(section: String): ApiResponseSerializer<TopStorySerializer> {
        val path = Constants.TOP_STORIES.plus("$section.json")
        val request = this.httpClient.request {
            method = HttpMethod.Get
            url {
                protocol = URLProtocol.HTTPS
                host = Constants.BASE_URL
                path(path)
                parameters.append("api-key", Constants.API_KEY)
            }
        }
        val response = request.body<ApiResponseSerializer<TopStorySerializer>>()
        Log.d("REQUEST", "${response.numResults}")
        return response
    }
}