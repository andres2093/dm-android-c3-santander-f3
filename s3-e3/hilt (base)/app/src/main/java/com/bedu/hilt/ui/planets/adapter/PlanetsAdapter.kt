package com.bedu.hilt.ui.planets.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bedu.hilt.R
import com.bedu.hilt.data.model.People
import com.bedu.hilt.databinding.ItemLayoutBinding
import com.bumptech.glide.Glide

class PlanetsAdapter(
    private val planets: ArrayList<People>,
) : RecyclerView.Adapter<PlanetsAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(model: People) {

            val binding = ItemLayoutBinding.bind(itemView)

            binding.textViewUserName.text = model.name
            binding.textViewUserEmail.text = model.gender
            Glide.with(binding.imageViewAvatar.context)
                .load("")
                .into(binding.imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = planets.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(planets[position])

    fun addData(list: List<People>) {
        planets.clear()
        planets.addAll(list)
    }
}