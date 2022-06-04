package com.example.appviagens.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TipoViagem(

    val tipo: String // lazer = 1 | negocios = 2

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}