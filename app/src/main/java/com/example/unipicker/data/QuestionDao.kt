package com.example.unipicker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {
    @Insert
    fun insertQuestion(question: Question)

    @Query("select * from question")
    fun getAllQuestions(): Flow<List<Question>>
}