package com.example.unipicker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grouping")
data class Grouping(
    @PrimaryKey
    val id: Int,
    val name: String
)
