package com.example.plantree.Viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.NavController
import com.example.plantree.Services.ArchiveService
import com.example.plantree.Services.archiveJsonToUser

class ContadorViewModel(application: Application) : AndroidViewModel(application) {

    private var navController: NavController? = null

    // Método para definir o NavController
    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    // Função que pega o número de árvores
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
}