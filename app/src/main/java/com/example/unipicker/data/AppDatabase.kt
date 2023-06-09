package com.example.unipicker.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class AppDatabase: RoomDatabase() {
    abstract fun groupingDao(): GroupatioDao
    abstract fun questionDao(): QuestionDao
    abstract fun facultyDao(): FacultyDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "unipicker.db"
                )
                    .createFromAsset("database/unipicker.db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}