package com.example.gardenappmvvm.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlant(plantEntity: Plant)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllPlants(plantEntityList: List<Plant>)

    @Query("Delete from plants_table")
    fun deleteAll()

    @Query("Select * from plants_table")
    fun getAllPlants(): LiveData<List<Plant>>

    @Query("Select * from plants_table where favorite=1")
    fun getFavoritePlants():LiveData<List<Plant>>
}