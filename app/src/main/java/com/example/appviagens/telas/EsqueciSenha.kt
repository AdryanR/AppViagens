package com.example.appviagens.telas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.appviagens.component.CustomTopAppBar

@Composable
fun EsqueciSenhaCompose(navController: NavHostController) {
    Scaffold(
        topBar = {
            CustomTopAppBar(navController, "Esqueci a senha", true)
        }, content = {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Esqueci a senha",
                    fontSize = 30.sp,
                    color = Color.Black
                )
            }

        })
}

