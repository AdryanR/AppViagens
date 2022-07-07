package com.example.testeloading

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.example.testeloading.ui.theme.TesteLoadingTheme
import kotlinx.coroutines.delay
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TesteLoadingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val stateLoading = remember { mutableStateOf<Boolean>(false) }
    val coroutineScope = rememberCoroutineScope()

    val doLoadingForAWhile: () -> Unit = {
        coroutineScope.launch {
            stateLoading.value = true
            delay(2000)
            stateLoading.value = false
        }
    }

    if (stateLoading.value) {
        ContentWhenLoading()
    } else {
        ContentWhenNotLoading(onClickLoading = doLoadingForAWhile)
    }
}

@Composable
fun ContentWhenLoading(){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(1f).background(color = Color.Gray)
    ) {
        Surface(modifier = Modifier.size(100.dp), shape = RoundedCornerShape(10.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Loading ...")
                Spacer(modifier = Modifier.size(20.dp))
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp),
                    color = Color.LightGray
                )
            }
        }
    }
}

@Composable
fun ContentWhenNotLoading(onClickLoading: () -> Unit){
    val context = LocalContext.current

    val showToast = {
        Toast.makeText(context, "This is a toast", Toast.LENGTH_SHORT).show()
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(1f)
    ) {
        Button(onClick = { showToast() }) {
            Text("Show toast")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { onClickLoading() }) {
            Text("Show progress for 2 seconds")
        }
    }
}