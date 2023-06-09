package com.example.unipicker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FacultyDao {
    @Insert
    fun insertFaculty(faculty: Faculty)

    @Query("select * from faculty")
    fun getAllFaculties(): Flow<List<Faculty>>
}