package com.example.unipicker.data

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface ResultsDao {
    @Insert
    fun insertResult(results: Results)
}