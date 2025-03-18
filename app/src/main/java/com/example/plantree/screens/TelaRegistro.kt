package br.com.fiap.testeaplicao.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantree.R

@Composable
fun TeladeRegistro(modifier: Modifier = Modifier) {
    var nome by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .background(color = Color(0xFF3DA941))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.tree),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 150.dp)
                    .fillMaxWidth()
                    .height(260.dp)
            )
            Text(
               text = "Tela de Registro",
                fontSize = 32.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                shape = RoundedCornerShape(topStart = 64.dp, topEnd = 64.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(28.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    InputField(label = "Nome", value = nome, onValueChange = { nome = it })
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = {},
                            shape = RoundedCornerShape(45.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3DA941), contentColor = Color.White)
                        ) {
                            Text("Avançar")
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = ""
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InputField(label: String, value: String, onValueChange: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF306C33),
            modifier = Modifier.align(Alignment.Start)
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color(0xFF306C33),
                focusedContainerColor = Color(0x0097FF9D),
                unfocusedContainerColor = Color(0x0097FF9D),
                cursorColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun TeladeRegistroPreview() {
    TeladeRegistro()
}
