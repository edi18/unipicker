package com.example.unipicker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

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
                    .allowMainThreadQueries()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}

val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Create a new temporary table with the desired column name
        database.execSQL("CREATE TABLE new_question (id INTEGER PRIMARY KEY, text TEXT, grouping INTEGER)")

        // Copy data from the old table to the new table
        database.execSQL("INSERT INTO new_question (id, text, grouping) SELECT id, name, grouping FROM question")

        // Drop the old table
        database.execSQL("DROP TABLE question")

        // Rename the new table to the original table name
        database.execSQL("ALTER TABLE new_question RENAME TO question")
    }
}

val MIGRATION_1_3: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Create a new temporary table with the desired column name
        database.execSQL("CREATE TABLE new_question (id INTEGER PRIMARY KEY, text TEXT NOT NULL, grouping INTEGER)")

        // Copy data from the old table to the new table
        database.execSQL("INSERT INTO new_question (id, text, grouping) SELECT id, text, grouping FROM question")

        // Drop the old table
        database.execSQL("DROP TABLE question")

        // Rename the new table to the original table name
        database.execSQL("ALTER TABLE new_question RENAME TO question")
    }
}