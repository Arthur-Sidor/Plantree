//package com.example.plantree.viewmodel

//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.plantree.network.testeDb
//import kotlinx.coroutines.launch

//class MainViewModel : ViewModel() {

// Estado para armazenar a resposta da API
// var apiResponse = mutableStateOf<String?>(null)

// Função para chamar a API e verificar a conexão com o banco de dados
//fun checkApiConnection() {
//   viewModelScope.launch {
// Chama a função 'testeDb' que está utilizando o Callback
//      testeDb { response ->
//         if (response != null && response.isNotEmpty()) {
// Aqui pegamos o conteúdo do primeiro item do array
//          apiResponse.value = "Conectado com sucesso! Resposta: ${response[0][0]}" // Pega o valor "Hello World"
//      } else {
//         // Caso contrário, falhou na conexão
//         apiResponse.value = "Falha ao conectar."
//     }
//  }
//  }
// }
//
