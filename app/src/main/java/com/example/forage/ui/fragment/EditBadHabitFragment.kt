package com.example.forage.ui.fragment

import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
import com.example.forage.R

class EditBadHabitFragment: Fragment() {
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu?.findItem(R.id.action_menu).isVisible = false
        menu?.findItem(R.id.menu_group).isVisible = false
    }
}