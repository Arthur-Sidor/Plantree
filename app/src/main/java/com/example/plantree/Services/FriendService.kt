package com.example.plantree.Services

import com.example.plantree.Services.Client.ApiClient
import com.example.plantree.model.AcceptFriendRequest
import com.example.plantree.model.DeleteFriendRequest
import com.example.plantree.model.Friend
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*
interface IFriendService {

    @POST("friend/add")
    fun createConnection(@Body friend: Friend): Call<ResponseBody>

    // Endpoint GET: Lista amigos do usuário
    @GET("friend/List")
    fun getFriends(@Query("key") userId: Int): Call<ResponseBody>

    // Endpoint GET: Lista pedidos de amizade pendentes
    @GET("friend/listPending")
    fun getPendingRequests(@Query("key") userId: Int): Call<ResponseBody>

    // Endpoint DELETE: Deleta uma conexão de amizade
    @DELETE("friend/delete")
    fun deleteFriend(@Body request: DeleteFriendRequest): Call<ResponseBody>

    // Endpoint PUT: Aceita uma conexão de amizade
    @PUT("friend/accept")
    fun acceptConnection(@Body request: AcceptFriendRequest): Call<ResponseBody>
}

fun createConnection(friend: Friend, callback: (String) -> Unit) {
    val apiService = ApiClient.createService(IFriendService::class.java)
    val call = apiService.createConnection(friend)
    call.enqueue(object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful) {
                try {
                    val jsonString = response.body()?.string() ?: "Resposta vazia"
                    val jsonObject = JSONObject(jsonString)
                    val message = jsonObject.getString("message")
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

fun getFriends(userId: Int, callback: (String) -> Unit) {
    val apiService = ApiClient.createService(IFriendService::class.java)
    val call = apiService.getFriends(userId)
    call.enqueue(object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful) {
                try {
                    val jsonString = response.body()?.string() ?: "Resposta vazia"
                    val jsonObject = JSONObject(jsonString)
                    val friendsArray = jsonObject.getJSONArray("friends")
                    val friendsList = mutableListOf<String>()
                    for (i in 0 until friendsArray.length()) {
                        val friend = friendsArray.getJSONObject(i)
                        friendsList.add("Nome: ${friend.getString("Nome")}, Árvores: ${friend.getInt("Nmr_Arvores")}")
                    }
                    callback("Amigos: ${friendsList.joinToString(", ")}")
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

fun getPendingRequests(userId: Int, callback: (String) -> Unit) {
    val apiService = ApiClient.createService(IFriendService::class.java)
    val call = apiService.getPendingRequests(userId)
    call.enqueue(object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful) {
                try {
                    val jsonString = response.body()?.string() ?: "Resposta vazia"
                    val jsonObject = JSONObject(jsonString)
                    val pendingFriendsArray = jsonObject.getJSONArray("pendingFriends")
                    val pendingFriendsList = mutableListOf<String>()
                    for (i in 0 until pendingFriendsArray.length()) {
                        val friend = pendingFriendsArray.getJSONObject(i)
                        pendingFriendsList.add("ID_U1: ${friend.getInt("ID_U1")}, ID_U2: ${friend.getInt("ID_U2")}")
                    }
                    callback("Pedidos pendentes: ${pendingFriendsList.joinToString(", ")}")
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

fun deleteFriend(request: DeleteFriendRequest, callback: (String) -> Unit) {
    val apiService = ApiClient.createService(IFriendService::class.java)
    val call = apiService.deleteFriend(request)
    call.enqueue(object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful) {
                try {
                    val jsonString = response.body()?.string() ?: "Resposta vazia"
                    val jsonObject = JSONObject(jsonString)
                    val message = jsonObject.getString("message")
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

fun acceptConnection(request: AcceptFriendRequest, callback: (String) -> Unit) {
    val apiService = ApiClient.createService(IFriendService::class.java)
    val call = apiService.acceptConnection(request)
    call.enqueue(object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful) {
                try {
                    val jsonString = response.body()?.string() ?: "Resposta vazia"
                    val jsonObject = JSONObject(jsonString)
                    val message = jsonObject.getString("message")
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

