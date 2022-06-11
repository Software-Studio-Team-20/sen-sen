package com.example.forage.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.forage.R
import com.example.forage.databinding.FragmentEditHabitBinding

class EditHabitFragment: Fragment() {
    private var _binding: FragmentEditHabitBinding?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu?.findItem(R.id.action_menu).isVisible = false
        menu?.findItem(R.id.menu_group).isVisible = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}