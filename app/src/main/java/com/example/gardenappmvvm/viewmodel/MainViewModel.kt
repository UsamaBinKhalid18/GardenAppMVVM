package com.example.gardenappmvvm.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.gardenappmvvm.data.Repository
import com.example.gardenappmvvm.data.database.Plant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
    var plantList = repository.getAllPlants()
    var favoriteList = repository.getFavorites()

    class MainViewModelFactory(val repository: Repository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }
    }

    suspend fun reset(context: Context) {
        repository.reset(context)
    }

    fun insertPlant(plant: Plant) {
        viewModelScope.launch(Dispatchers.IO) { repository.insertPlant(plant) }
    }
}