package com.example.gardenappmvvm.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gardenappmvvm.data.Repository

class MainViewModel(val repository: Repository) : ViewModel() {
    var plantList = repository.getAllPlants()

    class MainViewModelFactory(val repository: Repository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }
    }

    suspend fun reset(context: Context) {
        repository.reset(context)
    }
}