package com.example.gardenappmvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gardenappmvvm.R
import com.example.gardenappmvvm.data.database.Plant

class PlantsRVAdapter() : ListAdapter<Plant, PlantsRVAdapter.ViewHolder>(itemCallback) {

    companion object {
        object itemCallback : DiffUtil.ItemCallback<Plant>() {
            override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageViewPlant = view.findViewById<ImageView>(R.id.image_view_plant)
        val textViewPlantName = view.findViewById<TextView>(R.id.text_view_plant_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.textViewPlantName.text = item.name
        Glide.with(holder.itemView).load(item.imageUrl).into(holder.imageViewPlant)
    }
}