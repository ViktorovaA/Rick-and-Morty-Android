package com.example.kfd_test_project_1.common.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.core.content.ContextCompat.getSystemService
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


object NetworkModule {
    val publicClient = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                explicitNulls = false
                coerceInputValues = true
            })
        }
    }
}
