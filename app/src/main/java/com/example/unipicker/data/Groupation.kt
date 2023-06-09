package com.example.unipicker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "groupation")
data class Groupation(
    @PrimaryKey
    val id: Int,
    val name: String
)
