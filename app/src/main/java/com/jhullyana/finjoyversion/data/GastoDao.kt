package com.jhullyana.finjoyversion.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
@Dao
interface GastoDao {

    //Listar
    @Query("select * from gasto")
    fun listargastos(): Flow<List<Gasto>>


    //Buscar por Id
    @Query("select * from gasto where id = :idx")
    suspend fun buscarGastoPorId(idx: Int): Gasto

    //Gravar @Update @Insert
    @Upsert
    suspend fun gravarGasto(gasto: Gasto)

    @Delete
    suspend fun excluirGasto(gasto: Gasto)
}
