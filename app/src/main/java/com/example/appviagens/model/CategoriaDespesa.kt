package com.example.appviagens.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoriaDespesa(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val nome: String

) {
}