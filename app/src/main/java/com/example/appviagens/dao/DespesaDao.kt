package com.example.appviagens.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.appviagens.model.Despesa
import com.example.appviagens.model.DespesaCategoria

@Dao
interface DespesaDao {

    @Insert()
    suspend fun insert(despesa: Despesa)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(despesa: Despesa)

    @Transaction
    @Query("select * from Despesa d inner join CategoriaDespesa cd on d.categoriaID = cd.id where d.viagemID = :viagemID")
    fun allDespesasByViagem(viagemID: Int): LiveData<List<DespesaCategoria>>

}