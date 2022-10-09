package com.example.gardenappmvvm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gardenappmvvm.R
import com.example.gardenappmvvm.adapter.PlantsRVAdapter
import com.example.gardenappmvvm.databinding.FragmentAllPlantsBinding
import com.example.gardenappmvvm.viewmodel.MainViewModel

class AllPlantsFragment(private val viewModel: MainViewModel) : Fragment() {
    private lateinit var binding: FragmentAllPlantsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_all_plants, container, false)
        binding = FragmentAllPlantsBinding.bind(view)
        val adapter = PlantsRVAdapter(viewModel)
        binding.recyclerView.adapter = adapter

        viewModel.plantList.observe(viewLifecycleOwner) {
            adapter.submitList(it.sortedBy { plant -> plant.name })
        }
        return view
    }

}