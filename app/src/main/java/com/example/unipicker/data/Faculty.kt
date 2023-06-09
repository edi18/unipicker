package com.example.unipicker.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "faculty",
    foreignKeys = [
        ForeignKey(
            entity = Groupation::class,
            parentColumns = ["id"],
            childColumns = ["grouping"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Faculty(
    @PrimaryKey
    val id: Int,
    val name: String,
    val grouping: Int
)
