package com.example.appviagens.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class Pessoa : ViewModel() {

    var id by mutableStateOf("")

    var nome by mutableStateOf("")

    var login by mutableStateOf("")

    var senha by mutableStateOf("")


}