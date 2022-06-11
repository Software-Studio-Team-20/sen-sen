package com.example.forage.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.forage.R
import com.example.forage.data.HabitDatabase
import com.example.forage.databinding.ListItemHabitBinding
import com.example.forage.model.HabitItem

class HabitAdapter(
    private val clickListener: (HabitItem) -> Unit
) : ListAdapter<HabitItem, HabitAdapter.HabitViewHolder>(HabitDiffCallback) {

    class HabitViewHolder(
        private var binding: ListItemHabitBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(habitItem: HabitItem) {
            binding.habit = habitItem
            binding.executePendingBindings()

        }
    }

    companion object HabitDiffCallback : DiffUtil.ItemCallback<HabitItem>() {
        override fun areItemsTheSame(oldItem: HabitItem, newItem: HabitItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HabitItem, newItem: HabitItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HabitViewHolder(
            ListItemHabitBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habitItem = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener(habitItem)
        }
        // if(position.equals(-1)) holder.itemView.findViewById<View>(R.id.view).visibility = View.GONE
        holder.bind(habitItem)
    }


}