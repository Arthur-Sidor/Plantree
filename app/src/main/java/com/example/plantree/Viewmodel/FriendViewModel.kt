package com.example.plantree.Viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.plantree.Services.*
import com.example.plantree.model.AcceptFriendRequest
import com.example.plantree.model.DeleteFriendRequest
import com.example.plantree.model.Friend

class FriendViewModel : ViewModel() {

    // LiveData para armazenar o resultado das requisições
    private val _createConnectionResult = MutableLiveData<String>()
    val createConnectionResult: LiveData<String> get() = _createConnectionResult

    private val _getFriendsResult = MutableLiveData<String>()
    val getFriendsResult: LiveData<String> get() = _getFriendsResult

    private val _getPendingRequestsResult = MutableLiveData<String>()
    val getPendingRequestsResult: LiveData<String> get() = _getPendingRequestsResult

    private val _deleteFriendResult = MutableLiveData<String>()
    val deleteFriendResult: LiveData<String> get() = _deleteFriendResult

    private val _acceptConnectionResult = MutableLiveData<String>()
    val acceptConnectionResult: LiveData<String> get() = _acceptConnectionResult

    // Função para criar uma conexão de amizade
    fun createConnection(friend: Friend) {
        createConnection(friend) { result ->
            _createConnectionResult.postValue(result)
        }
    }

    // Função para buscar a lista de amigos
    fun getFriends(userId: Int) {
        getFriends(userId) { result ->
            _getFriendsResult.postValue(result)
        }
    }

    // Função para buscar pedidos de amizade pendentes
    fun getPendingRequests(userId: Int) {
        getPendingRequests(userId) { result ->
            _getPendingRequestsResult.postValue(result)
        }
    }

    // Função para deletar uma conexão de amizade
    fun deleteFriend(request: DeleteFriendRequest) {
        deleteFriend(request) { result ->
            _deleteFriendResult.postValue(result)
        }
    }

    // Função para aceitar uma conexão de amizade
    fun acceptConnection(request: AcceptFriendRequest) {
        acceptConnection(request) { result ->
            _acceptConnectionResult.postValue(result)
        }
    }
}