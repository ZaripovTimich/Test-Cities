package com.example.testcities.ui.listcities.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testcities.data.models.City
import com.example.testcities.databinding.ItemCityBinding

interface OnInteractionListener {
    fun onClickCity(id: Int)
}

class CitiesAdapter(
    private val onInteractionListener: OnInteractionListener
) : RecyclerView.Adapter<CityViewHolder>() {

    private var list = mutableListOf<City>()
    private var baseList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder =
        CityViewHolder(
            ItemCityBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            onInteractionListener
        )

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    fun setData(data: List<City>) {
        if (list.isNotEmpty()) list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    fun addData(data: List<City>) {
        val oldSize = list.size
        list.addAll(data)
        notifyItemRangeChanged(oldSize, list.size)
    }

    fun findCity(city: String) {
        list = baseList.filter { it.city.contains(city) }.toMutableList()
        notifyDataSetChanged()
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