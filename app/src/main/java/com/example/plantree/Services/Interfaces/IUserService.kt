package com.example.plantree.Services.Interfaces

import com.example.plantree.model.User
import retrofit2.Call
import retrofit2.http.*

interface IUserService {

    // Cria um novo usuario
    @POST("user/create")
    fun create(@Body user: User): Call<String>

    //Lista a quantidade de arvores que usuario tem ( Por ID)
    @GET("user/arvores")
    fun listQtddArvoresById(@Path("key") userId: Int): Call<Double>

    @GET("user/nome")
    fun getNameById( @Path("key") userId: Int): Call<String>

    @PATCH("user/edit")
    fun update(@Body user: User): Call<String>

}




