package com.example.appviagens.telas

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appviagens.ScreenManager

@Composable
fun Controle() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenManager.Login.route) {

        composable(ScreenManager.Login.route) {
            EstadoLogin(navController = navController)
        }

        composable(ScreenManager.Cadastro.route) {
            CadastroCompose(navController = navController)
        }

//        composable(ScreenManager.EsqueciSenha.route) { navBackStack ->
//            EsqueciSenhaCompose(navController = navController)
//        }

        composable(ScreenManager.Home.route) {
            HomeNavigation()
        }
    }
}