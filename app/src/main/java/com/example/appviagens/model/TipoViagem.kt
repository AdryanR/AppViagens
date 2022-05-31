package com.example.appviagens.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TipoViagem : ViewModel() {

    var id by mutableStateOf("")

    var tipo by mutableStateOf("") // lazer = 1 | negocios = 2


}