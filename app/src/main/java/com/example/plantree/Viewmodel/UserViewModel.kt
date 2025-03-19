package com.example.plantree.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.plantree.Services.*
import com.example.plantree.model.User

class UserViewModel : ViewModel() {

    // LiveData para armazenar o resultado das requisições
    private val _testResult = MutableLiveData<String>()
    val testResult: LiveData<String> get() = _testResult

    private val _createUserResult = MutableLiveData<String>()
    val createUserResult: LiveData<String> get() = _createUserResult

    private val _qtdArvoresResult = MutableLiveData<Int>()
    val qtdArvoresResult: LiveData<Int> get() = _qtdArvoresResult

    private val _userNameResult = MutableLiveData<String>()
    val userNameResult: LiveData<String> get() = _userNameResult

    private val _updateUserResult = MutableLiveData<String>()
    val updateUserResult: LiveData<String> get() = _updateUserResult

    // Função para testar a conexão com a API
    fun testApi() {
        teste { result ->
            _testResult.postValue(result)
        }
    }

    // Função para criar um novo usuário
    fun createUser(user: User) {
        createUser(user) { result ->
            _createUserResult.postValue(result.toString())
        }
    }

    // Função para buscar a quantidade de árvores de um usuário
    fun getQtdArvores(userId: Int) {
        listQtddArvoresById(userId) { result ->
            _qtdArvoresResult.postValue(result)
        }
    }

    // Função para buscar o nome de um usuário
    fun getUserName(userId: Int) {
        getNameById(userId) { result ->
            _userNameResult.postValue(result)
        }
    }

    // Função para atualizar os dados de um usuário
    fun updateUser(user: User) {
        updateUser(user) { result ->
            _updateUserResult.postValue(result)
        }
    }
}