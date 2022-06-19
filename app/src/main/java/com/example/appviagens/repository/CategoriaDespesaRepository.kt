package com.example.appviagens.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.appviagens.dao.CategoriaDao
import com.example.appviagens.dao.Connection
import com.example.appviagens.model.CategoriaDespesa

class CategoriaDespesaRepository(app: Application) {

    private val categoriaDao: CategoriaDao

    init {
        categoriaDao = Connection
            .getDB(app).CategoriaDao()
    }

    suspend fun save(categoria: CategoriaDespesa): Long {
        if (categoria.id == 0) {
            return categoriaDao.insert(categoria)
        } else {
            categoriaDao.update(categoria)
        }
        return 0
    }

    fun findAll(): LiveData<List<CategoriaDespesa>> {
        return categoriaDao.findAll()
    }

    suspend fun deleteByID(id : Int) {
        categoriaDao.deleteByID(id)
    }
}
