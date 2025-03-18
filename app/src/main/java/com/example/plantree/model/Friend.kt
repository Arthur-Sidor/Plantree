package com.example.plantree.model

data class Friend(
    val ID: Int,
    val idU1: Int,
    val idU2: Int,
    val isPending: Boolean,
    val isDelete: Boolean
)


// Modelo para a requisição de exclusão de amizade
data class DeleteFriendRequest(
    val KeyU1: Int,
    val KeyU2: Int
)

// Modelo para a requisição de aceitação de amizade
data class AcceptFriendRequest(
    val keyU1: Int,
    val keyU2: Int
)

