package com.example.plantree.Viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.plantree.Services.Client.ApiClient
import com.example.plantree.Services.IUserService
import com.example.plantree.model.User
import com.google.gson.Gson
import okio.IOException
import java.io.File
import java.io.FileInputStream
import java.nio.charset.StandardCharsets


class ConfigViewModel(private val userService: IUserService) : ViewModel() {

    // Função para atualizar o usuário
    fun updateUser(user: User, callback: (String) -> Unit) {
        // Cria o serviço API
        val apiService = ApiClient.createService(IUserService::class.java)

        // Chama o metodo updateUser da IUserService
        val call = apiService.updateUser(user)}}

// Função para ler o usuário do arquivo JSON
fun archiveJsonToUser(context: Context): User? {
    // Define o nome do arquivo e o diretório
    val fileName = "user.json"
    val directory = File(context.getExternalFilesDir(null), "content")
    val file = File(directory, fileName)

    // Verifica se o arquivo existe
    if (!file.exists()) {
        return null
    }

    return try {
        FileInputStream(file).use { inputStream ->
            // Lê o conteúdo do arquivo
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)

            // Converte o conteúdo para uma string
            val jsonString = String(buffer, StandardCharsets.UTF_8)

            // Usa o Gson para desserializar o JSON em um objeto User
            val gson = Gson()
            gson.fromJson(jsonString, User::class.java)
        }
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}