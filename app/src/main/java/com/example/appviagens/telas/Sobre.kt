package com.example.appviagens.telas

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Surfing
import androidx.compose.material.icons.rounded.Work
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appviagens.R
import com.example.appviagens.component.AppBarTelas
import com.example.appviagens.ui.theme.Gainsoro
import com.example.appviagens.ui.theme.White

@Composable
fun SobreCompose() {
    Scaffold(
        topBar = { AppBarTelas("Sobre o app", Icons.Rounded.Person) }
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(20.dp))
            Image(
                painter = painterResource(R.drawable.man17),
                contentDescription = "Imagem em circulo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape) // clip to the circle shape
                    .border(
                        5.dp,
                        Color.Gray,
                        CircleShape
                    )//optional
            )
            Spacer(modifier = Modifier.padding(11.dp))
            Text(
                text = "Sobre o app",
                style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Aplicativo desenvolvido com a recente tecnologia Jetpack Compose e a linguaguem Kotlin " +
                        "para a matéria de Desenvolvimento Mobile, do 5º semestre do curso de ADS - Senac, 2022.",
                style = TextStyle(fontSize = 17.sp, lineHeight = 25.sp, textAlign = TextAlign.Center)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Por Adryan Rafael da Silva",
                style = TextStyle(fontSize = 22.sp, fontFamily = FontFamily.Cursive)
            )
            Spacer(modifier = Modifier.height(14.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Image(
                    painter = painterResource(R.drawable.icons8_whatsapp_384),
                    contentDescription = "Imagem em circulo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape) // clip to the circle shape
                        .border(
                            5.dp,
                            Color.Gray,
                            CircleShape
                        )//optional
                        .clickable { }
                )
                Image(
                    painter = painterResource(R.drawable.icons8_linkedin_circundado_250),
                    contentDescription = "Imagem em circulo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape) // clip to the circle shape
                        .border(
                            5.dp,
                            Color.Gray,
                            CircleShape
                        )//optional
                        .clickable { }
                )
                Image(
                    painter = painterResource(R.drawable.icons8_github_240),
                    contentDescription = "Imagem em circulo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape) // clip to the circle shape
                        .border(
                            2.dp,
                            Color.Gray,
                            CircleShape
                        )//optional
                        .clickable { }
                )
            }
        }
    }
}