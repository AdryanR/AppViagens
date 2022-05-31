package com.example.appviagens.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

// tentei usar o construtor pra gerar a lista mas não deu...
// id: Int, destino: String, dataPartida: String, dataChegada: String, orcamento: Double, tipoID: Int, usuarioID: Int
class Viagem() : ViewModel() {

    var id by mutableStateOf(0)

    var destino by mutableStateOf("")

    var tipoID by mutableStateOf("") // lazer = 1 | negocios = 2

    var dataChegada by mutableStateOf("")

    var dataPartida by mutableStateOf("")

    var orcamento by mutableStateOf(0.00)

    var usuarioID by mutableStateOf("")


}