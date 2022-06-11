package com.example.forage.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.forage.R
import com.example.forage.data.TutorialDataSource

class TutorialAdapter : RecyclerView.Adapter<TutorialAdapter.TutorialViewHolder>(){
    private val dataset = TutorialDataSource().loadTutorial()

    class TutorialViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imageView: ImageView = view.findViewById(R.id.image)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorialViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_tutorial, parent, false)
        return TutorialViewHolder(layout)
    }

    override fun onBindViewHolder(holder: TutorialViewHolder, position: Int) {
        val item = dataset[position]

        holder.imageView.setImageResource(item.imageId)
    }
}