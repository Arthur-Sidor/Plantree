package com.example.contadordasarborigena

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun FriendsScreen(onBack: NavHostController) {
    var selectedTab by remember { mutableStateOf(0) }
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val systemUiController = rememberSystemUiController()

    val tabBackgroundColor = Color(0xFF121212)
    val textColor = Color.White
    val backgroundColor = Color(0xFF121212)
    val cardColor = Color(0xFF1E1E1E)
    val buttonColor = Color(0xFF3DA941)

    SideEffect {
        systemUiController.setStatusBarColor(color = tabBackgroundColor)
    }

    Column(
        modifier = Modifier.fillMaxSize().background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().background(tabBackgroundColor).padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = tabBackgroundColor,
                contentColor = textColor
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { Text("Amigos", fontSize = 18.sp, color = textColor) }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { Text("Pedidos Pendentes", fontSize = 18.sp, color = textColor) }
                )
            }
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Pesquisar...") },
                modifier = Modifier.fillMaxWidth(0.9f).padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier.fillMaxWidth(0.9f).fillMaxHeight(0.8f).background(cardColor, shape = RoundedCornerShape(30.dp)),
            contentAlignment = Alignment.TopCenter
        ) {
            if (selectedTab == 0) FriendsList(searchQuery.text) else PendingRequestsList()
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {        // Navega para a tela "contador"
                onBack.navigate("contador")},
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Text("Voltar", color = Color.White)
        }
    }
}

@Composable
fun FriendsList(searchQuery: String) {
    val friends = listOf("McQueen", "Cleusa Magalhães", "Bucky Barnes", "Oppenheimer", "Dean Winchester", "Eren Yager", "Roblox da Silva")
    val filteredFriends = friends.filter { it.contains(searchQuery, ignoreCase = true) }
    LazyColumn { items(filteredFriends) { FriendItem(it) } }
}

@Composable
fun PendingRequestsList() {
    val pendingRequests = listOf("Ricardo", "Juliana", "Beatriz", "Marcos")
    LazyColumn { items(pendingRequests) { FriendRequestItem(it) } }
}

@Composable
fun FriendItem(name: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        Text(text = name, fontSize = 18.sp, modifier = Modifier.padding(16.dp), color = Color.White)
    }
}

@Composable
fun FriendRequestItem(name: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = name, fontSize = 18.sp, color = Color.White)
            Row {
                Button(
                    onClick = { /* Aceitar */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3DA941)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Aceitar", color = Color.White)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { /* Recusar */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC93036)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Recusar", color = Color.White)
                }
            }
        }
    }
}
