package com.example.appviagens.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.appviagens.model.Despesa

@Dao
interface DespesaDao {

    @Insert()
    suspend fun insert(despesa: Despesa)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(despesa: Despesa)

    @Transaction
    @Query(
        "select d.id AS id, d.descricao AS descricao, d.valor AS valor, d.local AS local, d.data " +
                "AS data, d.categoriaID AS categoriaID,d.viagemID AS viagemID, cd.nome AS nome from Despesa d " +
                "inner join CategoriaDespesa cd on d.categoriaID = cd.id where d.viagemID = :viagemID"
    )
    fun allDespesasByViagem(viagemID: Int): LiveData<List<DespesaCategoria>>

    data class DespesaCategoria(
        var id: Int = 0,
        val descricao: String,
        val valor: Double,
        val local: String,
        val data: String,
        val categoriaID: Int,
        val viagemID: Int,
        val nome: String
    )

    @Query("select * from Despesa d where d.id = :id")
    suspend fun findById(id: Int): Despesa

    @Query("Delete from Despesa where Despesa.id = :id")
    suspend fun deleteByID(id: Int)

}