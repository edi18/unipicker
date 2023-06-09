package com.example.unipicker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupatioDao {
    @Insert
    fun insertGroupation(groupation: Groupation)

    @Query("select * from groupation")
    fun getAllGroupations(): Flow<List<Groupation>>

    @Query("select * from groupation where id = :id")
    fun getGroupation(id: Int): Flow<Groupation>
}