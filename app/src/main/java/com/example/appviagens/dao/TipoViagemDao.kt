package com.example.appviagens.dao

import androidx.room.*
import com.example.appviagens.model.TipoViagem

@Dao
interface TipoViagemDao {

    @Insert()
    suspend fun insert(tipoViagem: TipoViagem)


}