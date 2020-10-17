package io.github.manuelernesto.androidroomdemo.data

import androidx.room.*

@Dao
interface EventoDAO {

    @Insert
    suspend fun salvar(evento: Evento)

    @Query("SELECT * FROM evento")
    suspend fun buscarTodos(): List<Evento>

    @Update
    suspend fun actualizar(evento: Evento)

    @Delete
    suspend fun apagar(evento: Evento)
}