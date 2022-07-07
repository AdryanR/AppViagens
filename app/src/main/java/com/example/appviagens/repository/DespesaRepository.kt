package com.example.appviagens.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.appviagens.dao.Connection
import com.example.appviagens.dao.DespesaDao
import com.example.appviagens.model.Despesa

class DespesaRepository(app: Application) {

    private val despesaDao: DespesaDao

    init {
        despesaDao = Connection
            .getDB(app).DespesaDao()
    }

    suspend fun save(despesa: Despesa) {
        if (despesa.id == 0) {
            despesaDao.insert(despesa)
        } else {
            despesaDao.update(despesa)
        }
    }

    fun allDespesasByViagem(viagemID: Int): LiveData<List<DespesaDao.DespesaCategoria>> {
        return despesaDao.allDespesasByViagem(viagemID)
    }

    suspend fun findById(id: Int): Despesa {
        return despesaDao.findById(id)
    }

    suspend fun deleteByID(id: Int) {
        despesaDao.deleteByID(id)
    }


}