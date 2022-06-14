package com.example.forage.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.forage.R
import com.example.forage.data.SettingsListDataSource
import com.example.forage.databinding.ListItemHabitBinding
import com.example.forage.databinding.ListItemVoiceDataBinding
import com.example.forage.model.HabitItem
import com.example.forage.model.VoiceDataItem

class VoiceDataAdapter(
    private val clickListener: (VoiceDataItem) -> Unit
) : ListAdapter<VoiceDataItem, VoiceDataAdapter.VoiceDataViewHolder>(VoiceDataDiffCallback) {

    class VoiceDataViewHolder(
        private var binding: ListItemVoiceDataBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item: VoiceDataItem) {
            binding.data = item
            binding.executePendingBindings()
        }
    }

    companion object VoiceDataDiffCallback: DiffUtil.ItemCallback<VoiceDataItem>() {
        override fun areItemsTheSame(oldItem: VoiceDataItem, newItem: VoiceDataItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: VoiceDataItem, newItem: VoiceDataItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoiceDataViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return VoiceDataViewHolder(
            ListItemVoiceDataBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VoiceDataViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener(item)
        }

        holder.bind(item)
    }
}