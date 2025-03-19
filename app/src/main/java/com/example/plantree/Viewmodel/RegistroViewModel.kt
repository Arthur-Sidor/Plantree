package com.example.plantree.Viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.plantree.Services.ArchiveService
import com.example.plantree.Services.createUser
import com.example.plantree.model.User
import kotlinx.coroutines.launch

class RegistroViewModel(application: Application) : AndroidViewModel(application) {

    private val archiveService = ArchiveService()
    private var navController: NavController? = null

    // Método para definir o NavController
    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun createUser(name: String) {
        viewModelScope.launch {
            // Cria um novo objeto User com o nome fornecido
            val user = User(nome = name, key = 0, nmrArvores = 0) // Inicializa a chave como vazia

            // Chama o método createUser que você já implementou
            com.example.plantree.Services.createUser(user) { response ->
                // Atualiza a chave do usuário com o ID retornado pela API
                user.key = response

                // Cria o arquivo de log com o usuário atualizado
                createLogFile(user) { success ->
                    if (success) {
                        // Redireciona para a tela Home
                        navController?.navigate("home")
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