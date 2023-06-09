package com.example.unipicker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {
    @Insert
    fun insertQuestion(question: Question)

    @Query("select * from question order by random()")
    fun getAllQuestions(): List<Question>

    @Query("select * from question where grouping = :grouping")
    fun getAllQuestionsFromGrouping(grouping: Int): Flow<Question>
}