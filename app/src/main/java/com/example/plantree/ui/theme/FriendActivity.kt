package com.example.plantree.ui.theme

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.plantree.R
import com.example.plantree.viewmodel.FriendViewModel

class FriendActivity : AppCompatActivity() {

    private val friendViewModel: FriendViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend)

        friendViewModel.pendingRequests.observe(this, Observer { requests ->
            // Atualizar UI com a lista de pedidos pendentes
        })

        friendViewModel.friends.observe(this, Observer { friends ->
            // Atualizar UI com a lista de amigos
        })

        friendViewModel.isDeleted.observe(this, Observer { success ->
            if (success) {
                // Notificar sucesso ao excluir
            }
        })

        friendViewModel.isAccepted.observe(this, Observer { success ->
            if (success) {
                // Notificar sucesso ao aceitar a conexão
            }
        })

        friendViewModel.newFriend.observe(this, Observer { friend ->
            if (friend != null) {
                // Notificar sucesso ao criar nova conexão
            }
        })

        // Exemplo de uso:
        friendViewModel.getPendingRequests()
    }
}
