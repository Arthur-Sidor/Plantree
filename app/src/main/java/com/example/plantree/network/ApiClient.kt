package com.example.plantree.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor // Importa corretamente o HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient.Builder

object ApiClient {

    // Use 10.0.2.2 para acessar o localhost do computador no emulador
    private const val BASE_URL = "http://10.0.2.2:3000/"

    // Cria um OkHttpClient personalizado
    private val okHttpClient by lazy {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Log completo das requisições
        }

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS) // Timeout de conexão
            .readTimeout(30, TimeUnit.SECONDS) // Timeout de leitura
            .writeTimeout(30, TimeUnit.SECONDS) // Timeout de escrita
            .build()
    }

    // Cria uma instância do Retrofit
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient) // Usa o OkHttpClient personalizado
            .addConverterFactory(GsonConverterFactory.create()) // Converte JSON para objetos Kotlin
            .build()
    }

    // Expõe o serviço da API
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}