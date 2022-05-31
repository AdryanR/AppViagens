package com.example.appviagens.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appviagens.model.Pessoa

@Database(entities = arrayOf(Pessoa::class), version = 1)
abstract class Connection : RoomDatabase() {

    abstract fun pessoaDao(): PessoaDao
    //abstract fun outroDao(): OutroDao

    // Desing Pattern - Singleton
    companion object {
        var connection: Connection? = null

        fun getDB(context: Context): Connection {
            val temp = connection
            if (temp != null) {
                return temp
            } else {
                // conectar o banco
                val instance = Room.databaseBuilder(
                    context,
                    Connection::class.java,
                    "dbAppViagens"
                ).build()
                connection = instance
                return instance
            }
        }

    }
}