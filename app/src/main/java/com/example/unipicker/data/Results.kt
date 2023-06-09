package com.example.unipicker.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "results",
    foreignKeys = [
        ForeignKey(
            entity = Groupation::class,
            parentColumns = ["id"],
            childColumns = ["grouping"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Results (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val grouping: Int,
    val points: Int
)