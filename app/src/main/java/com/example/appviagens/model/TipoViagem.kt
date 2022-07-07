package com.example.appviagens.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

//@Entity(
//    indices = [Index(
//        value = ["tipo"],
//        unique = true
//    )]
//)
@Entity(indices = [Index(value = arrayOf("tipo"), unique = true)])
data class TipoViagem(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "tipo")
    val tipo: String // lazer = 1 | negocios = 2

)