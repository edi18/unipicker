package com.example.unipicker.ui.question

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.unipicker.UnipickerApplication
import com.example.unipicker.data.*
import kotlinx.coroutines.flow.Flow

class QuestionViewModel(
    private val questionDao: QuestionDao,
    private val groupatioDao: GroupatioDao,
    private val facultyDao: FacultyDao,
): ViewModel() {
    fun getAllQuestions(): Flow<List<Question>> = questionDao.getAllQuestions()
    fun getAllQuestionsFromGrouping(grouping: Int): Flow<Question> = questionDao.getAllQuestionsFromGrouping(grouping)
    fun getGroupation(id: Int): Flow<Groupation> = groupatioDao.getGroupation(id)
    fun getAllGroupations(): Flow<List<Groupation>> = groupatioDao.getAllGroupations()

    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as UnipickerApplication)
                QuestionViewModel(
                    application.database.questionDao(),
                    application.database.groupingDao(),
                    application.database.facultyDao()
                )
            }
        }
    }
}