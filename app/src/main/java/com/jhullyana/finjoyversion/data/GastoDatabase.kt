package com.jhullyana.finjoyversion.data

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Gasto::class], version = 1)
abstract class GastoDatabase: RoomDatabase(){
    abstract fun gastoDao(): GastoDao

    companion object {
        fun abrirBancoDeDados(context: Context): GastoDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                GastoDatabase::class.java, "gasto.db"
            ).build()
        }
    }
}