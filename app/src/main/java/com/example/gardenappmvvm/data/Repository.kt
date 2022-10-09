package com.example.gardenappmvvm.data

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.gardenappmvvm.data.database.Plant
import com.example.gardenappmvvm.data.database.PlantsDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.reflect.Type

class Repository(val database: PlantsDatabase) {

    fun getAllPlants(): LiveData<List<Plant>> {
        return database.getPlantDao().getAllPlants()
    }

    suspend fun insertPlant(plant: Plant) {
        database.getPlantDao().insertPlant(plant)
    }

    suspend fun reset(context: Context) {
        database.getPlantDao().deleteAll()
        withContext(Dispatchers.IO) {
            val jsonString = context.applicationContext.assets.open("flowers.json").reader()
                .readText()
            val listType: Type = object : TypeToken<ArrayList<Plant?>?>() {}.type
            val plantList = Gson().fromJson<List<Plant>>(jsonString, listType)
            database.getPlantDao().insertAllPlants(plantList)
        }
    }

    fun getFavorites(): LiveData<List<Plant>> {
        return database.getPlantDao().getFavoritePlants()
    }
}