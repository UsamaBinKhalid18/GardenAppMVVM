package com.example.gardenappmvvm.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.gardenappmvvm.R
import com.example.gardenappmvvm.adapter.TabsAdapter
import com.example.gardenappmvvm.databinding.ActivityMainBinding
import com.example.gardenappmvvm.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance


class MainActivity : AppCompatActivity(),KodeinAware {
    override val kodein by closestKodein()
    private val factory:MainViewModel.MainViewModelFactory by instance()
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        binding.viewPager.adapter = TabsAdapter(this, viewModel)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                1->"Favorite Plants"
                else -> {
                    "All Plants"
                }
            }
        }.attach()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.all_plants_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.reset_all -> {
                CoroutineScope(Dispatchers.Default).launch { viewModel.reset(baseContext) }
                true
            }
            else -> false
        }
    }
}