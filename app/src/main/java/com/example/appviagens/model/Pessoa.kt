package com.example.appviagens.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pessoa(
    val nome: String,
    val login: String,
    val senha: String,
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}