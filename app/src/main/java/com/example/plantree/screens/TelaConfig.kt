package br.com.fiap.testeaplicao.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SettingsScreen()
        }
    }
}

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 2.dp, vertical = 5.dp)
    ) {
        // Cabeçalho
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .background(color = Color(0xFF1E1E1E), shape = RoundedCornerShape(8.dp))
                .padding(vertical = 16.dp, horizontal = 125.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Configurações",
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Itens de configuração
        SettingItem(title = "Nome de Usuário", value = "-", showEditButton = true)
        SettingItem(title = "Key do Usuário", value = "-")

        // Botão de Logout
        LogoutButton()

        // Botão Voltar
        BackButton()
    }
}

@Composable
fun SettingItem(title: String, value: String, showEditButton: Boolean = false, color: Color = Color.White) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                color = Color.Gray,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp, bottom = 4.dp)
            )
            Text(
                text = value,
                color = color,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        if (showEditButton) {
            Button(
                onClick = { },
                modifier = Modifier
                    .padding(start = 8.dp, end = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E1E1E))
            ) {
                Text(text = "Editar", fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun LogoutButton() {
    Button(
        onClick = { /* Ação de logout aqui */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .wrapContentWidth(Alignment.CenterHorizontally),  // Centraliza horizontalmente
        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
        shape = RoundedCornerShape(8.dp)  // Bordas arredondadas
    ) {
        Text(
            text = "Logout",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun BackButton() {
    Button(
        onClick = { /* Ação de voltar aqui */ },
        modifier = Modifier
            .fillMaxWidth() // Ocupa toda a largura da tela
            .padding(top = 550.dp),  // Espaço acima do botão
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3DA941)), // Cor de fundo verde
        shape = RoundedCornerShape(8.dp)  // Bordas arredondadas
    ) {
        Text(
            text = "Voltar",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen()
}
