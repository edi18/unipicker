package com.example.unipicker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupingDao {
    @Insert
    fun insertGrouping(grouping: Grouping)

    @Query("select * from grouping")
    fun getAllGroupings(): Flow<List<Grouping>>
}