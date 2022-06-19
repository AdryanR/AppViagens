package com.example.appviagens.telas

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.appviagens.R
import com.example.appviagens.ScreenManager
import com.example.appviagens.component.PasswordField
import com.example.appviagens.model.Pessoa
import com.example.appviagens.viewModel.*

@Composable
fun EstadoLogin(navController: NavHostController) {
    var isLogged by remember {
        mutableStateOf(false)
    }
    var loginUser by remember {
        mutableStateOf("")
    }
    if (isLogged) {
        //CircularProgressIndicator(modifier = Modifier.size(width = 100.dp, height = 100.dp))
        navController.navigate(ScreenManager.Home.route)
    } else {
        LoginCompose(
            onUserChange = {
                loginUser = it
            },
            onSuccess = {
                isLogged = true
            },
            navController = navController
        )
    }
}

@Composable
fun LoginCompose(
//    login: String, // PEGAR O NOME, PODE SER O ID DEPOIS...
    onUserChange: (String) -> Unit,
    onSuccess: () -> Unit,
    navController: NavHostController
) {
    Box(modifier = Modifier.fillMaxSize()) {
        ClickableText(
            text = AnnotatedString("Cadastre-se"),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp),
            onClick = { navController.navigate(ScreenManager.Cadastro.route) },
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
//                color = Purple700
            )
        )
    }
    Column(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val ctx = LocalContext.current
        val app = ctx.applicationContext as Application
        val model:
                PessoaViewModel = viewModel(
            factory = PessoaViewModelFactory(app)
        )

        Image(
            painter = painterResource(R.drawable.logo),
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
        Text(text = "Login", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Login") },
            value = model.login,
            onValueChange = { model.login = it })

        Spacer(modifier = Modifier.height(20.dp))
        PasswordField(value = model.senha, onChange = { model.senha = it })

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { //if ( model.login("teste", "teste") !! > 0) { }
                    onSuccess()
                 },
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .width(350.dp)
                .height(50.dp)
        ) {
            Text(text = "Logar")
        }

        Spacer(modifier = Modifier.height(20.dp))
        ClickableText(
            text = AnnotatedString("Esqueceu a senha?"),
            onClick = { navController.navigate(ScreenManager.EsqueciSenha.route) },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default
            )
        )
    }
}
