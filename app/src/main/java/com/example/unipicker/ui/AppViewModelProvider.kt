package com.example.unipicker.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import com.example.unipicker.ui.home.HomeViewModel
import androidx.lifecycle.viewmodel.CreationExtras

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                this.createSavedStateHandle()
            )
        }
    }
}

fun CreateionExtras.application() :