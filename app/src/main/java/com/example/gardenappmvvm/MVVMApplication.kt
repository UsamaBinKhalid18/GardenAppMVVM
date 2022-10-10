package com.example.gardenappmvvm

import android.app.Application
import com.example.gardenappmvvm.data.Repository
import com.example.gardenappmvvm.data.database.PlantsDatabase
import com.example.gardenappmvvm.viewmodel.MainViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication:Application(),KodeinAware {
    override val kodein=Kodein.lazy {
        bind() from singleton { PlantsDatabase.getInstance(this@MVVMApplication) }
        bind()from singleton { Repository(instance()) }
        bind() from provider { MainViewModel.MainViewModelFactory(instance()) }
    }

}