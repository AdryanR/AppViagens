package com.example.appviagens.repository

import android.app.Application
import com.example.appviagens.dao.Connection
import com.example.appviagens.dao.TipoViagemDao
import com.example.appviagens.model.TipoViagem
import com.example.appviagens.model.Viagem

class TipoViagemRepository(app: Application) {

    private val tipoViagemDao: TipoViagemDao

    //val allViagensByUser: LiveData<List<Viagem>>

    init {
        tipoViagemDao = Connection
            .getDB(app).tipoViagemDao()
        //allViagensByUser = viagemDao.getViagensByUser() // select all
    }

    suspend fun save(tipoViagem: TipoViagem) {
        if (tipoViagem.id == 0) {
            tipoViagemDao.insert(tipoViagem)
        }
    }
}