package com.example.unipicker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Question::class, Groupation::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun groupingDao(): GroupatioDao
    abstract fun questionDao(): QuestionDao

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