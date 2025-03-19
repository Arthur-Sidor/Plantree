package com.example.plantree

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.plantree.ui.theme.PlantreeTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.testeaplicao.screens.HomeScreen
import br.com.fiap.testeaplicao.screens.TeladeRegistro
import com.example.contadordasarborigena.TreeCounterScreen
import br.com.fiap.testeaplicao.screens.*
import com.example.contadordasarborigena.FriendsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PlantreeTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "inicial"
                ){
                    composable(route = "inicial") { HomeScreen(navController) }
                    composable(route = "registro") { TeladeRegistro(navController)}
                    composable(route = "contador") { TreeCounterScreen(navController) }
                    composable(route = "configuracoes") { SettingsScreen(navController) }
                    composable(route = "friends") { FriendsScreen(navController) }
                }

            }
        }
    }
}
