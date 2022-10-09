package com.example.gardenappmvvm.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.reflect.Type

@Database(entities = [Plant::class], version = 1, exportSchema = false)
abstract class PlantsDatabase : RoomDatabase() {

    abstract fun getPlantDao(): PlantDao

    companion object {
        @Volatile
        private var instance: PlantsDatabase? = null

        fun getInstance(context: Context): PlantsDatabase {
            val temp = instance
            if (temp != null) {
                return temp
            }
            synchronized(this) {
                val tempInst =
                    Room.databaseBuilder(
                        context.applicationContext,
                        PlantsDatabase::class.java,
                        "Plants.db"
                    ).addCallback(
                        object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                val jsonString =
                                    context.applicationContext.assets.open("flowers.json").reader()
                                        .readText()
                                val listType: Type =
                                    object : TypeToken<ArrayList<Plant?>?>() {}.type
                                val plantList = Gson().fromJson<List<Plant>>(jsonString, listType)
                                CoroutineScope(Dispatchers.IO).launch {
                                    getInstance(context).getPlantDao().insertAllPlants(plantList)
                                }
                            }
                        }
                    ).build()
                instance = tempInst
                return tempInst
            }
        }
    }
}