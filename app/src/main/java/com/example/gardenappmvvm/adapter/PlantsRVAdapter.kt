package com.example.gardenappmvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gardenappmvvm.R
import com.example.gardenappmvvm.data.database.Plant
import com.example.gardenappmvvm.viewmodel.MainViewModel

class PlantsRVAdapter(val viewModel: MainViewModel) :
    ListAdapter<Plant, PlantsRVAdapter.ViewHolder>(itemCallback) {

    companion object {
        object itemCallback : DiffUtil.ItemCallback<Plant>() {
            override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
                return oldItem.name == newItem.name && oldItem.favorite==newItem.favorite

            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageViewPlant = view.findViewById<ImageView>(R.id.image_view_plant)
        val textViewPlantName = view.findViewById<TextView>(R.id.text_view_plant_name)
        val buttonFavorite = view.findViewById<ImageButton>(R.id.button_favorite)
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
        holder.buttonFavorite.setImageResource(
            if (item.favorite) R.drawable.ic_star_fill
            else R.drawable.ic_star)
        holder.buttonFavorite.setOnClickListener {
            item.favorite = !item.favorite
            (it as ImageButton).setImageResource(
                if (item.favorite) R.drawable.ic_star_fill
                else R.drawable.ic_star)
            viewModel.insertPlant(item)
        }
    }
}