package com.example.forage.data

import com.example.forage.R
import com.example.forage.model.TutorialItem

class TutorialDataSource {
    fun loadTutorial(): List<TutorialItem> {
        return listOf(
            TutorialItem(R.drawable.tutorial1),
            TutorialItem(R.drawable.tutorial2),
            TutorialItem(R.drawable.tutorial3),
            TutorialItem(R.drawable.tutorial4),
            TutorialItem(R.drawable.tutorial5)
        )
    }
}