package com.example.gardenappmvvm.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plants_table")
data class Plant(
    @PrimaryKey
    val name: String,
    val plantId: String,
    val description: String,
    val wateringInterval: Int,
    val growZoneNumber: Int,
    val imageUrl: String,
    var favorite:Boolean=false
)