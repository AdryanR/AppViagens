package com.example.appviagens.telas

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.appviagens.ScreenManager
import com.example.appviagens.component.CustomTopAppBar
import com.example.appviagens.component.PasswordField
import com.example.appviagens.viewModel.Pessoa

@Composable
fun CadastroCompose(navController: NavHostController) {
    Scaffold(
        topBar = {
            CustomTopAppBar(navController, "Cadastro", true)
        }, content = {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            }

        })
    val model: Pessoa = viewModel()
    Column(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(com.example.appviagens.R.drawable.logo),
            contentDescription = "Imagem em circulo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(74.dp)
                .clip(CircleShape) // clip to the circle shape
                .border(
                    5.dp,
                    Color.Gray,
                    CircleShape
                )//optional
        )
        Spacer(modifier = Modifier.padding(15.dp))
        Text(
            text = "Cadastro",
            style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive)
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            label = { Text(text = "Nome") },
            value = model.nome,
            onValueChange = { model.nome = it })
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            label = { Text(text = "Login") },
            value = model.login,
            onValueChange = { model.login = it })

        Spacer(modifier = Modifier.height(20.dp))
        PasswordField(value = model.senha, onChange = { model.senha = it })
        Spacer(modifier = Modifier.height(20.dp))
        PasswordField(value = model.senha, onChange = { model.senha = it }, label = "Confirme a senha")

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { navController.navigate(ScreenManager.Login.route) },
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .width(350.dp)
                .height(50.dp)
        ) {
            Text(text = "Cadastrar-se")
        }

    }
}
