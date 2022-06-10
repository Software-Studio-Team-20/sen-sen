package com.example.forage.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.forage.databinding.ListItemBadHabitBinding
import com.example.forage.databinding.ListItemHabitBinding
import com.example.forage.model.BadHabitItem
import com.example.forage.model.HabitItem

class BadHabitAdapter(
    private val clickListener: (BadHabitItem) -> Unit
) : ListAdapter<BadHabitItem, BadHabitAdapter.BadHabitViewHolder>(BadHabitAdapter) {

    class BadHabitViewHolder(
        private var binding: ListItemBadHabitBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(habitItem: BadHabitItem) {
            binding.habit = habitItem
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<BadHabitItem>() {
        override fun areItemsTheSame(oldItem: BadHabitItem, newItem: BadHabitItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BadHabitItem, newItem: BadHabitItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BadHabitAdapter.BadHabitViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BadHabitAdapter.BadHabitViewHolder(
            ListItemBadHabitBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BadHabitAdapter.BadHabitViewHolder, position: Int) {
        val habitItem = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener(habitItem)
        }
        // if(position.equals(-1)) holder.itemView.findViewById<View>(R.id.view).visibility = View.GONE
        holder.bind(habitItem)
    }
}