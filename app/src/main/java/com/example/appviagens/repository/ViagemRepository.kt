package com.example.appviagens.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.appviagens.dao.Connection
import com.example.appviagens.dao.ViagemDao
import com.example.appviagens.model.Viagem

class ViagemRepository(app: Application) {

    private val viagemDao: ViagemDao

    init {
        viagemDao = Connection
            .getDB(app).viagemDao()
    }

    suspend fun save(viagem: Viagem) {
        if (viagem.id == 0) {
            viagemDao.insert(viagem)
        } else {
            viagemDao.update(viagem)
        }
    }

    fun allViagensByUser(userID: Int): LiveData<List<Viagem>> {
        return viagemDao.getViagensByUser(userID)
    }

    suspend fun findById(id: Int): Viagem {
        return viagemDao.findById(id)
    }

    suspend fun deleteByID(id: Int) {
        viagemDao.deleteByID(id)
    }

    fun somaDespesasByViagem(idViagem: Int): LiveData<Double> {
        return viagemDao.somaDespesasByViagem(idViagem)
    }

//    suspend fun insertTipos(tipoviagem: TipoViagem) {
//        viagemDao.insertTipos(tipoviagem)
//    }


}