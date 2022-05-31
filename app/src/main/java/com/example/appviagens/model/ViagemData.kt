package com.example.appviagens.model

data class ViagemData(
    val id: Int,
    val destino: String,
    val dataPartida: String,
    val dataChegada: String,
    val orcamento: Double,
    val tipoID: Int,
    val usuarioID: Int
) {

}
