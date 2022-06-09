package com.example.forage.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.forage.R
import com.example.forage.data.SettingsListDataSource
import com.google.android.material.internal.ContextUtils.getActivity
import java.security.AccessController.getContext

class SettingsAdapter : RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>(){

    private val dataset = SettingsListDataSource().loadSettings()

    class SettingsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val nameView: TextView = view.findViewById(R.id.name)
        val descriptionView: TextView = view.findViewById(R.id.description)
        val button: ImageButton = view.findViewById(R.id.imageButton)
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

        holder.nameView.text = holder.nameView.context.getString(item.name)
        holder.descriptionView.text = holder.descriptionView.context.getString(item.description)

        holder.button.setOnClickListener{

        }
    }
}