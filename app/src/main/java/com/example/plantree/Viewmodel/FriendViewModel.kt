package com.example.plantree.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.plantree.model.AcceptFriendRequest
import com.example.plantree.model.DeleteFriendRequest
import com.example.plantree.model.Friend
import com.example.plantree.model.User
import com.example.plantree.Services.*

class FriendViewModel(application: Application) : AndroidViewModel(application) {

    fun getFriends(userId: Int, callback: (List<User>) -> Unit) {
        getFriends(userId) {
                message -> callback(message)
        }
    }

    fun getPendingRequests(userId: Int, callback: (String) -> Unit) {
        getPendingRequests(userId) { pendingRequests ->
            callback(pendingRequests)
        }
    }

    fun deleteFriend(request: DeleteFriendRequest, callback: (String) -> Unit) {
        deleteFriend(request) { message ->
            callback(message)
        }
    }

    fun acceptConnection(request: AcceptFriendRequest, callback: (String) -> Unit) {
        acceptConnection(request) { message ->
            callback(message)
        }
    }

    // Hydra Dominatus!
}