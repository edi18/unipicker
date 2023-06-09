package com.example.unipicker.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "question",
    foreignKeys = [
        ForeignKey(
            entity = Groupation::class,
            parentColumns = ["id"],
            childColumns = ["grouping"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Question(
    @PrimaryKey
    val id: Int,
    val text: String,
    val grouping: Int
)
