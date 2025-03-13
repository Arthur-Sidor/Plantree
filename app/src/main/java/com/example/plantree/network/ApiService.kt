package com.example.plantree.network

import com.example.plantree.model.Friend
import com.example.plantree.model.User
import retrofit2.Call
import retrofit2.http.*
import retrofit2.Callback
import retrofit2.Response
interface ApiService {

    // Endpoint GET: Lista pedidos pendentes de conexão
    @GET("friend/pending")
    fun getPendingRequests(): Call<List<Friend>>

    // Endpoint GET: Lista amigos do usuário
    @GET("friend/friends/{userId}")
    fun getFriends(@Path("userId") userId: Int): Call<List<Friend>>

    @GET("test-db")
    fun testeDb() : Call<String>

    // Endpoint DELETE: Deleta a conexão com outro usuário
    @DELETE("friend/{friendId}")
    fun deleteFriend(@Path("friendId") friendId: Int): Call<Void>

    // Endpoint POST: Aceita a conexão
    @POST("friend/accept")
    fun acceptConnection(@Body friend: Friend): Call<Void>

    // Endpoint POST: Cria a conexão
    @POST("friend/create")
    fun createConnection(@Body friend: Friend): Call<Friend>
}

// Função `testeDb` modificada para usar um callback
fun testeDb(callback: (String?) -> Unit) {
    val call = ApiClient.apiService.testeDb()
    call.enqueue(object : Callback<String> {
        override fun onResponse(call: Call<String>, response: Response<String>) {
            if (response.isSuccessful) {
                val responseBody = response.body()
                println("Resposta do testeDb: $responseBody")
                callback(responseBody) // Retorna a resposta via callback
            } else {
                println("Erro na resposta: ${response.errorBody()?.string()}")
                callback(null) // Retorna null em caso de erro
            }
        }

        override fun onFailure(call: Call<String>, t: Throwable) {
            println("Falha na requisição: ${t.message}")
            callback(null) // Retorna null em caso de falha de rede
        }
    })
}