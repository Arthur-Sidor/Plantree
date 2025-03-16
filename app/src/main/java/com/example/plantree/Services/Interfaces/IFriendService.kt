package com.example.plantree.Services.Interfaces

import com.example.plantree.model.Friend
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IFriendService {

    // Endpoint GET: Lista pedidos pendentes de conexão
    @GET("friend/pending")
    fun getPendingRequests(): Call<List<Friend>>

    // Endpoint GET: Lista amigos do usuário
    @GET("friend/friends/{userId}")
    fun getFriends(@Path("userId") userId: Int): Call<List<Friend>>

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