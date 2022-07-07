package com.example.appviagens.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.appviagens.ui.theme.Purple700
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CircularProgressBarLoadingLogin(isLoading: Boolean) {
    if (isLoading) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize(1f)
                .background(color = Color.DarkGray)
            //.blur(50.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Buscando...", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.size(20.dp))
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp),
                    color = Purple700
                )
            }
        }
    }
}

@Composable
fun CircularProgressBarLoading() {
    val stateLoading = remember { mutableStateOf<Boolean>(true) }
    val coroutineScope = rememberCoroutineScope()
    val doLoadingForAWhile: () -> Unit = {
        coroutineScope.launch {
            delay(250)
            stateLoading.value = false
        }
    }
    if (stateLoading.value) {
        doLoadingForAWhile.invoke()
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(50.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(35.dp),
                color = Purple700
            )
        }
    }
    return
}