package com.example.unipicker

import android.app.Application
import com.example.unipicker.data.AppDatabase

class UnipickerApplication: Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}