package com.example.appviagens.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.appviagens.dao.Connection
import com.example.appviagens.dao.ViagemDao
import com.example.appviagens.model.Viagem

class ViagemRepository(app: Application) {

    private val viagemDao: ViagemDao

    //val allViagensByUser: LiveData<List<Viagem>>

    init {
        viagemDao = Connection
            .getDB(app).viagemDao()
        //allViagensByUser = viagemDao.getViagensByUser() // select all
    }

    suspend fun save(viagem: Viagem) {
        if (viagem.id == 0) {
            viagemDao.insert(viagem)
        }
        else {
            viagemDao.update(viagem)
        }
    }

    fun allViagensByUser(userID : Int) : LiveData<List<Viagem>> {
        return viagemDao.getViagensByUser(userID)
    }

    suspend fun findById(id : Int) : Viagem {
        return viagemDao.findById(id)
    }

    suspend fun deleteByID(id : Int) {
        viagemDao.deleteByID(id)
    }

    fun somaDespesasByViagem(idViagem : Int) : LiveData<Double> {
        return viagemDao.somaDespesasByViagem(idViagem)
    }

//    suspend fun insertTipos(tipoviagem: TipoViagem) {
//        viagemDao.insertTipos(tipoviagem)
//    }


    //val allViagensByUser : LiveData<List<PessoaViagem>> = viagemDao.getUsuarioComViagens() // select all

    // ???? val log : Int? = viagemDao.login(login = "", senha = "" )

//    suspend fun findAll(): List<Pessoa> = pessoaDao.findAll()
//
//    suspend fun findById(id: Int) = pessoaDao.findById(id)
//
//    suspend fun delete(contato: Pessoa) = pessoaDao.delete(contato)
//
//    suspend fun login(login: String, senha: String): Int? = pessoaDao.login(login,senha)




}