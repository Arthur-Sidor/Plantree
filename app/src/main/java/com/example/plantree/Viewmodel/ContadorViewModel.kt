package com.example.plantree.Viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.compose.runtime.key
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.plantree.Services.ArchiveService
import com.example.plantree.Services.archiveJsonToUser
import com.example.plantree.Services.updateUser
import com.example.plantree.model.User
import kotlinx.coroutines.launch
import com.example.plantree.Services.IUserService as IUserService

class ContadorViewModel(application: Application) : AndroidViewModel(application) {

    private var navController: NavController? = null

    // Método para definir o NavController
    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun navegateTo(url: String){
        navController?.navigate(url)
    }

    // Função que pega o número de árvores
    fun getNmrArvores(context: Context): Any? {
        // Chama a função archiveJsonToUser para ler o arquivo JSON e converter para um objeto User
        val user = archiveJsonToUser(context)

        // Verifica se o usuário foi encontrado
        return if (user != null) {
            user.nmrArvores // Retorna o número de árvores
        } else {
            navController?.navigate("initial") // Redireciona para a tela inicial
            null
        }
    }

    //Adiciona +1 no count (alterando o arquivo logo depois o bd)
    fun addInNmrArvore() {
        val context = getApplication<Application>().applicationContext
        val archiveService = ArchiveService()

        // Chama o método readUserJsonFile
        val _user = archiveService.readUserJsonFile(context)

        // Verifica se _user.nome não é null
        if (_user.nome == null) {

            _user.nome = "";
        }

        var user = User(nome = _user.nome, key = _user.key, nmrArvores = (_user.nmrArvores + 1))

        archiveService.updateUserJsonFile(context, user)

        updateUser(user) { response ->
            println(response) // Exibe a resposta da API
        }
    }
    //Remove 1 no count (alterando o arquivo logo depois o bd)
    @SuppressLint("SuspiciousIndentation")
    fun subInNmrArvore(){
            // Obtém o contexto da aplicação
            val context = getApplication<Application>().applicationContext
            val archiveService = ArchiveService()

            // Chama o método readUserJsonFile
            val _user = archiveService.readUserJsonFile(context)

        var nmrArvores = 0

        if (_user.nmrArvores <= 0) {
            nmrArvores = 0
        }
        // Verifica se _user.nome não é null
        if (_user.nome == null) {

            _user.nome = "";
        }
        var user = User(nome = _user.nome, key = _user.key, nmrArvores = nmrArvores)

            archiveService.updateUserJsonFile(context,user);

        updateUser(user) { response ->
            println(response) // Exibe a resposta da API
        }

    }


    //Remove 1 no count (alterando o arquivo logo depois o bd)
}