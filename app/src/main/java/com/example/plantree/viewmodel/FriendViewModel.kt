package com.example.plantree.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.plantree.model.Friend
import com.example.plantree.repository.FriendRepository

class FriendViewModel(application: Application) : AndroidViewModel(application) {

    private val friendRepository = FriendRepository()
    val pendingRequests = MutableLiveData<List<Friend>>()
    val friends = MutableLiveData<List<Friend>>()
    val isDeleted = MutableLiveData<Boolean>()
    val isAccepted = MutableLiveData<Boolean>()
    val newFriend = MutableLiveData<Friend?>()

    fun getPendingRequests() {
        friendRepository.getPendingRequests { response ->
            pendingRequests.postValue(response)
        }
    }

    fun getFriends(userId: Int) {
        friendRepository.getFriends(userId) { response ->
            friends.postValue(response)
        }
    }

    fun deleteFriend(friendId: Int) {
        friendRepository.deleteFriend(friendId) { success ->
            isDeleted.postValue(success)
        }
    }

    fun acceptConnection(friend: Friend) {
        friendRepository.acceptConnection(friend) { success ->
            isAccepted.postValue(success)
        }
    }

    fun createConnection(friend: Friend) {
        friendRepository.createConnection(friend) { response ->
            newFriend.postValue(response)
        }
    }
}
