package com.example.plantree

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plantree.ui.theme.PlantreeTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import com.example.plantree.Services.Interfaces.createTables

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlantreeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // Estado para armazenar a resposta da API
    var apiResponse by remember { mutableStateOf<String?>(null) }

    // Usa Column para organizar os componentes verticalmente
    Column(
        modifier = modifier.padding(16.dp), // Adiciona um padding geral
        horizontalAlignment = Alignment.CenterHorizontally // Centraliza os componentes horizontalmente
    ) {
        // Botão que chama a função testeDb
        Button(
            onClick = {
                // Chama a função testeDb e atualiza o estado com a resposta
                createTables { response ->
                    apiResponse = response.toString()
                }
            }
        ) {
            Text("Chamar testeDb")
        }

        // Exibe a resposta da API abaixo do botão
        if (apiResponse != null) {
            Text(
                text = "Resposta da API: $apiResponse",
                modifier = Modifier.padding(top = 16.dp), // Adiciona um espaço acima do texto
                color = Color.Black,
                fontSize = 18.sp
            )
        } else {
            Text(
                text = "Nenhuma resposta ainda.",
                modifier = Modifier.padding(top = 16.dp), // Adiciona um espaço acima do texto
                color = Color.Gray,
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PlantreeTheme {
        Greeting("Android")
    }
}