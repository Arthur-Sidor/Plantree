package com.example.plantree.repository

import com.example.plantree.model.Friend
import com.example.plantree.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FriendRepository {

    fun getPendingRequests(callback: (List<Friend>?) -> Unit) {
        ApiClient.apiService.getPendingRequests().enqueue(object : Callback<List<Friend>> {
            override fun onResponse(call: Call<List<Friend>>, response: Response<List<Friend>>) {
                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<List<Friend>>, t: Throwable) {
                callback(null)
            }
        })
    }

    fun getFriends(userId: Int, callback: (List<Friend>?) -> Unit) {
        ApiClient.apiService.getFriends(userId).enqueue(object : Callback<List<Friend>> {
            override fun onResponse(call: Call<List<Friend>>, response: Response<List<Friend>>) {
                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<List<Friend>>, t: Throwable) {
                callback(null)
            }
        })
    }

    fun deleteFriend(friendId: Int, callback: (Boolean) -> Unit) {
        ApiClient.apiService.deleteFriend(friendId).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                callback(response.isSuccessful)
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                callback(false)
            }
        })
    }

    fun acceptConnection(friend: Friend, callback: (Boolean) -> Unit) {
        ApiClient.apiService.acceptConnection(friend).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                callback(response.isSuccessful)
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                callback(false)
            }
        })
    }

    fun createConnection(friend: Friend, callback: (Friend?) -> Unit) {
        ApiClient.apiService.createConnection(friend).enqueue(object : Callback<Friend> {
            override fun onResponse(call: Call<Friend>, response: Response<Friend>) {
                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<Friend>, t: Throwable) {
                callback(null)
            }
        })
    }
}
