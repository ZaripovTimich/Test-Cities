package com.example.testcities.ui.listcities.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testcities.data.models.City
import com.example.testcities.databinding.ItemCityBinding

interface OnInteractionListener {
    fun onClickCity(id: Int)
}

class CitiesAdapter(
    private val onInteractionListener: OnInteractionListener
) : ListAdapter<City, CityViewHolder>(CityDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder =
        CityViewHolder(
            ItemCityBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onInteractionListener
        )

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CityViewHolder(
    private val binding: ItemCityBinding,
    private val onInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: City) {
        binding.tvCity.text = "${item.city}, ${item.country}"
        binding.itemCity.setOnClickListener {
            onInteractionListener.onClickCity(item.id)
        }
    }
}

class CityDiffCallback : DiffUtil.ItemCallback<City>() {
    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem == newItem
    }
}