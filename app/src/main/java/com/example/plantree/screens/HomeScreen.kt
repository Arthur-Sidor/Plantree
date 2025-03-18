package br.com.fiap.testeaplicao.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.plantree.R
import com.example.plantree.Viewmodel.LoginViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(navController: NavController?) {
    // Obtém a instância da LoginViewModel
    val viewModel: LoginViewModel = viewModel()

    LaunchedEffect(Unit) {
        // Chama o método getLogFile da ViewModel
        viewModel.getLogFile { user ->
            if (user == null) {
                // Redireciona para a tela de registro apenas se o user for null
                navController?.navigate("registro")
            }
        }

        // Aguarda 3 segundos antes de prosseguir (opcional)
        kotlinx.coroutines.delay(3000)
    }

    Box(
        modifier = Modifier
            .background(color = Color(0xFF3DA941))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.tree), // Uso correto do recurso
                contentDescription = "Árvore",
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .height(260.dp)
            )
            Text(
                text = "Plantree",
                fontSize = 32.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                modifier = Modifier.padding(top = 30.dp)
            )

            CircularProgressIndicator(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .size(50.dp),
                color = Color(0xFF306C33),
                strokeWidth = 4.dp
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(null)
}