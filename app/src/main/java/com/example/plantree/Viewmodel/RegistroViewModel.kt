package com.example.plantree.Viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantree.Services.ArchiveService
import com.example.plantree.Services.createUser
import com.example.plantree.model.User
import kotlinx.coroutines.launch

class RegistroViewModel(application: Application) : AndroidViewModel(application) {

    private val archiveService = ArchiveService()

    fun createUser(user: User, callback: (Int) -> Unit) {
        viewModelScope.launch {
            // Chama o método createUser que você já implementou
            com.example.plantree.Services.createUser(user) { response ->
                // Atualiza a chave do usuário com o ID retornado pela API
                user.key = response

                // Cria o arquivo de log com o usuário atualizado
                createLogFile(user) { success ->
                    if (success) {
                        // Retorna o ID do usuário via callback
                        callback(response)
                    } else {
                        // Retorna um valor inválido em caso de falha
                        callback(-1)
                    }
                }
            }
        }
    }

    // Cria o arquivo de log com o usuário atualizado
    private fun createLogFile(user: User, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            // Obtém o contexto da aplicação
            val context = getApplication<Application>().applicationContext

            // Chama o método para criar o arquivo JSON
            val success = archiveService.createUserJsonFile(context, user)

            // Retorna o resultado via callback
            callback(success)
        }
    }
}