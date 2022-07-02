package com.example.appviagens.dao

import androidx.room.*
import com.example.appviagens.model.Pessoa

@Dao
interface PessoaDao {

    @Insert()
    suspend fun insert(pessoa: Pessoa)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(pessoa: Pessoa)

//    @Delete
//    suspend fun delete(pessoa: Pessoa)
//
//    @Query("select * from Pessoa order by nome")
//    suspend fun findAll(): List<Pessoa>
//
//    @Query("select * from Pessoa c where c.id = :id")
//    suspend fun findById(id: Int): Pessoa?

    @Query("select * from Pessoa p where p.login = :login and p.senha = :senha")
    suspend fun login(login: String, senha: String): Pessoa?

}