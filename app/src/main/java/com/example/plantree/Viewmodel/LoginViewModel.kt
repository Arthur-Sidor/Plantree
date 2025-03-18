package com.example.plantree.Viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantree.Services.ArchiveService
import com.example.plantree.model.User
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val archiveService = ArchiveService()

    fun getLogFile(callback: (User?) -> Unit) {
        viewModelScope.launch {
            // Obtém o contexto da aplicação
            val context = getApplication<Application>().applicationContext

            // Chama o método readUserJsonFile
            val user = archiveService.readUserJsonFile(context)

            // Retorna o resultado via callback
            callback(user)
        }
    }
}