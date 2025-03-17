package com.example.plantree.Services

import com.example.plantree.Services.Client.ApiClient
import com.example.plantree.model.User
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*

interface IUserService {

    @GET("test-db")
    fun teste(): Call<ResponseBody> // Retorna o corpo da resposta como ResponseBody

    // Cria um novo usuario
    @POST("user/create")
    fun create(@Body user: User): Call<ResponseBody>

    // Retorna a quantidade de árvores de um usuário pelo ID
    @GET("user/arvores")
    fun listQtddArvoresById(@Query("key") userId: Int): Call<ResponseBody>

    // Retorna o nome de um usuário pelo ID
    @GET("user/nome")
    fun getNameById(@Query("key") userId: Int): Call<ResponseBody>

    // Atualiza os dados de um usuário
    @PATCH("User/Edit")
    fun update(@Body user: User): Call<ResponseBody>
}

// Método para fazer a chamada à API
fun teste(callback: (String) -> Unit) {
    val apiService = ApiClient.createService(IUserService::class.java)
    val call = apiService.teste()
    call.enqueue(object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful) {
                try {
                    // Converte o JSON da resposta em uma string
                    val jsonString = response.body()?.string() ?: "Resposta vazia"

                    // Faz o parsing do JSON para extrair a string desejada
                    val jsonObject = JSONObject(jsonString)
                    val dataObject = jsonObject.getJSONObject("data")
                    val message = dataObject.getString("message")

                    // Retorna a string via callback
                    callback(message)
                } catch (e: Exception) {
                    callback("Erro ao processar JSON: ${e.message}")
                }
            } else {
                callback("Erro na resposta: ${response.errorBody()?.string()}")
            }
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            callback("Falha na requisição: ${t.message}")
        }
    })
}

fun createUser(user: User, callback: (String) -> Unit) {
    val apiService = ApiClient.createService(IUserService::class.java)
    val call = apiService.create(user)
    call.enqueue(object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful) {
                try {
                    // Converte o JSON da resposta em uma string
                    val jsonString = response.body()?.string() ?: "Resposta vazia"

                    // Faz o parsing do JSON para extrair a mensagem e o ID do usuário
                    val jsonObject = JSONObject(jsonString)
                    val message = jsonObject.getString("message")
                    val userId = jsonObject.getInt("userId")

                    // Retorna a mensagem de sucesso via callback
                    callback("$message | ID do usuário: $userId")
                } catch (e: Exception) {
                    callback("Erro ao processar JSON: ${e.message}")
                }
            } else {
                callback("Erro na resposta: ${response.errorBody()?.string()}")
            }
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            callback("Falha na requisição: ${t.message}")
        }
    })
}

fun listQtddArvoresById(userId: Int, callback: (String) -> Unit) {
    val apiService = ApiClient.createService(IUserService::class.java)
    val call = apiService.listQtddArvoresById(userId)
    call.enqueue(object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful) {
                try {
                    // Converte o JSON da resposta em uma string
                    val jsonString = response.body()?.string() ?: "Resposta vazia"

                    // Faz o parsing do JSON para extrair a quantidade de árvores
                    val jsonObject = JSONObject(jsonString)
                    val nmrArvores = jsonObject.getInt("nmr_arvores")

                    // Retorna a quantidade de árvores via callback
                    callback("Quantidade de árvores: $nmrArvores")
                } catch (e: Exception) {
                    callback("Erro ao processar JSON: ${e.message}")
                }
            } else {
                callback("Erro na resposta: ${response.errorBody()?.string()}")
            }
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            callback("Falha na requisição: ${t.message}")
        }
    })
}

fun getNameById(userId: Int, callback: (String) -> Unit) {
    val apiService = ApiClient.createService(IUserService::class.java)
    val call = apiService.getNameById(userId)
    call.enqueue(object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful) {
                try {
                    // Converte o JSON da resposta em uma string
                    val jsonString = response.body()?.string() ?: "Resposta vazia"

                    // Faz o parsing do JSON para extrair o nome
                    val jsonObject = JSONObject(jsonString)
                    val nome = jsonObject.getString("nome")

                    // Retorna o nome via callback
                    callback("Nome do usuário: $nome")
                } catch (e: Exception) {
                    callback("Erro ao processar JSON: ${e.message}")
                }
            } else {
                callback("Erro na resposta: ${response.errorBody()?.string()}")
            }
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            callback("Falha na requisição: ${t.message}")
        }
    })
}

fun updateUser(user: User, callback: (String) -> Unit) {
    val apiService = ApiClient.createService(IUserService::class.java)
    val call = apiService.update(user)
    call.enqueue(object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful) {
                try {
                    // Converte o JSON da resposta em uma string
                    val jsonString = response.body()?.string() ?: "Resposta vazia"

                    // Retorna a mensagem de sucesso via callback
                    callback("Resposta da API: $jsonString")
                } catch (e: Exception) {
                    callback("Erro ao processar JSON: ${e.message}")
                }
            } else {
                callback("Erro na resposta: ${response.errorBody()?.string()}")
            }
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            callback("Falha na requisição: ${t.message}")
        }
    })
}



