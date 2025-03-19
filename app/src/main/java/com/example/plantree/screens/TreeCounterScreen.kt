package com.example.contadordasarborigena

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.plantree.R
import com.example.plantree.Viewmodel.ContadorViewModel

@Composable
fun TreeCounterScreen(onFriendsClick: NavHostController) {

    var treeCount by remember { mutableStateOf(0) }
    val viewModel: ContadorViewModel = viewModel()
    val context = LocalContext.current // Obtém o contexto atual

    // Dispara a ação quando a tela é carregada
    LaunchedEffect(Unit) {
        val nmrArvores = viewModel.getNmrArvores(context)
        if (nmrArvores is Int) {
            treeCount = nmrArvores // Atualiza o estado com o número de árvores
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.tree),
                contentDescription = "Árvore",
                modifier = Modifier.size(250.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))
//3DA941FF
            Box(
                modifier = Modifier
                    .width(380.dp)
                    .height(280.dp)
                    .background(Color(0xFF1E1E1E), shape = RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Button(
                            onClick = { if (treeCount > 0) treeCount-- },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3DA941))
                        ) {
                            Text(text = "-", fontSize = 24.sp, color = Color.White)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "$treeCount",
                            color = Color.White,
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(
                            onClick = { treeCount++ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3DA941))
                        ) {
                            Text(text = "+", fontSize = 24.sp, color = Color.White)
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = if (treeCount > 0) "Parabéns! Você plantou $treeCount árvores!" else "Vamos plantar árvores?",
                        color = Color.White,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(30.dp))
                    Row {
                        Button(
                            onClick = { /* TODO: Adicionar ação de configurações */ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3DA941)),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(text = "Configurações", color = Color.White)
                        }

                        Spacer(modifier = Modifier.width(20.dp))

                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3DA941)),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(text = "Amigos", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}
