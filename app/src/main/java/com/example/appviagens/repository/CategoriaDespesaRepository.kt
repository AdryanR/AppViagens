package com.example.appviagens.repository

import android.app.Application
import com.example.appviagens.dao.CategoriaDao
import com.example.appviagens.dao.Connection
import com.example.appviagens.model.CategoriaDespesa

class CategoriaDespesaRepository(app: Application) {

    private val categoriaDao: CategoriaDao

    init {
        categoriaDao = Connection
            .getDB(app).CategoriaDao()
    }

    suspend fun save(categoria: CategoriaDespesa) {
        if (categoria.id == 0) {
            categoriaDao.insert(categoria)
        } else {
            categoriaDao.update(categoria)
        }
    }
}
