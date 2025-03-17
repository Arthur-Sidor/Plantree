package com.example.plantree.Services.Client

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    // URL base do servidor (ajuste para o IP do seu servidor)
    private const val BASE_URL = "http://10.0.2.2:3000/"

    // Interceptor para logar as requisições e respostas
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Loga o corpo das requisições/respostas
    }

    // Configuração do OkHttpClient com interceptores e timeouts
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor) // Adiciona o interceptor de logging
        .connectTimeout(30, TimeUnit.SECONDS) // Timeout de conexão
        .readTimeout(30, TimeUnit.SECONDS) // Timeout de leitura
        .build()

    // Configuração do Retrofit (privada)
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL) // Define a URL base
        .client(okHttpClient) // Usa o OkHttpClient configurado
        .addConverterFactory(GsonConverterFactory.create()) // Conversor para JSON
        .build()

    // Função compatível com Java
    fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }
}