package com.example.gardenappmvvm.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gardenappmvvm.R
import com.example.gardenappmvvm.adapter.PlantsRVAdapter
import com.example.gardenappmvvm.databinding.FragmentAllPlantsBinding
import com.example.gardenappmvvm.databinding.FragmentFavoriteBinding
import com.example.gardenappmvvm.viewmodel.MainViewModel


class FavoriteFragment(private val viewModel: MainViewModel) : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_favorite, container, false)
        binding=FragmentFavoriteBinding.bind(view)
        val adapter=PlantsRVAdapter(viewModel)
        binding.recyclerView.adapter=adapter
        viewModel.favoriteList.observe(viewLifecycleOwner){
            adapter.submitList(it.sortedBy { plant->plant.name })
        }
        return view
    }


}