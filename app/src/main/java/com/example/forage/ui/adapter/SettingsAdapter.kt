package com.example.forage.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.forage.R
import com.example.forage.data.SettingsListDataSource
import com.example.forage.model.SettingsListItem
import com.example.forage.model.VoiceDataItem

class SettingsAdapter(
    private val clickListener: (SettingsListItem) -> Unit
) : RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>(){

    private val dataset = SettingsListDataSource().loadSettings()

    class SettingsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val nameView: TextView = view.findViewById(R.id.name)
        val descriptionView: TextView = view.findViewById(R.id.description)
        val button: ImageButton = view.findViewById(R.id.imageButton)
        val divider: View = view.findViewById(R.id.view)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_settings, parent, false)
        return SettingsViewHolder(layout)
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        val item = dataset[position]

        holder.nameView.text = holder.nameView.context.getString(item.nameId)
        holder.descriptionView.text = holder.descriptionView.context.getString(item.descriptionId)

        if(position.equals(dataset.size-1)) holder.divider.visibility = View.GONE


        holder.button.setOnClickListener{
            clickListener(item)
        }
    }
}