package com.example.appviagens.telas

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FlightTakeoff
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.appviagens.component.AppBarTelas

@Composable
fun SobreCompose() {
    Scaffold(
        topBar = { AppBarTelas("Sobre o app", Icons.Rounded.Person) }
    ) {
        Text(
            text = "Sobre",
            fontSize = 30.sp,
            color = Color.Black
        )
    }
}