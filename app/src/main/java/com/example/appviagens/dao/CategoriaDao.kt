package com.example.appviagens.dao

import androidx.room.*
import com.example.appviagens.model.CategoriaDespesa

@Dao
interface CategoriaDao {

    @Insert()
    suspend fun insert(categoria: CategoriaDespesa)
//
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(categoria: CategoriaDespesa)
//
//    @Delete
//    suspend fun delete(categoria: CategoriaDespesa)

}