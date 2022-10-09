package com.example.gardenappmvvm.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gardenappmvvm.ui.fragment.AllPlantsFragment
import com.example.gardenappmvvm.viewmodel.MainViewModel

class TabsAdapter(fragmentActivity: FragmentActivity, val viewModel: MainViewModel) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 1

    override fun createFragment(position: Int): Fragment = when (position) {
        else -> AllPlantsFragment(viewModel)
    }
}