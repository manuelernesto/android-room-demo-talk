package io.github.manuelernesto.androidroomdemo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "evento")
data class Evento(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "codigo")
    var id: Long = 0,
    val titulo: String,
    val descricao: String,
    val palestrante: String,
    val data: Long
) : Serializable