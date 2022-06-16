package com.example.forage.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.forage.BaseApplication
import com.example.forage.R
import com.example.forage.databinding.FragmentEditBadHabitBinding
import com.example.forage.model.BadHabitItem
import com.example.forage.ui.viewmodel.BadHabitViewModel
import com.example.forage.ui.viewmodel.BadHabitViewModelFactory

class EditBadHabitFragment : Fragment() {
    private val navigationArgs: EditBadHabitFragmentArgs by navArgs()

    private lateinit var habitItem: BadHabitItem

    private val viewModel: BadHabitViewModel by activityViewModels {
        BadHabitViewModelFactory(
            (activity?.application as BaseApplication).badHabitDatabase.badHabitDao()
        )
    }

    private var _binding: FragmentEditBadHabitBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBadHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.id
        viewModel.getBadHabitById(id).observe(this.viewLifecycleOwner) { selectedItem ->
            habitItem = selectedItem
            bindHabitItem(habitItem)
        }
    }

    fun bindHabitItem(habitItem: BadHabitItem){
        binding.apply {
            nameInput.setText(habitItem.name, TextView.BufferType.SPANNABLE)
            goalsInput.setText(habitItem.goal, TextView.BufferType.SPANNABLE)
            frequencyInput.setText(habitItem.frequency, TextView.BufferType.SPANNABLE)
            timeRangeInput.setText(habitItem.timeRange, TextView.BufferType.SPANNABLE)
            reminderInput.setText(habitItem.reminderMesseage, TextView.BufferType.SPANNABLE)
            noteInput.setText(habitItem.note, TextView.BufferType.SPANNABLE)
            saveButton.setOnClickListener{
                updateHabit()
            }
            cancelButton.setOnClickListener{
                val action = EditBadHabitFragmentDirections
                    .actionEditBadHabitFragmentToViewBadHabitFragment(habitItem.id)
                findNavController().navigate(action)
            }
        }
    }

    private fun updateHabit() {
        if (isValidEntry()) {
            viewModel.updateBadHabit(
                id = navigationArgs.id,
                name = binding.nameInput.text.toString(),
                goal = binding.goalsInput.text.toString(),
                frequency = binding.frequencyInput.text.toString(),
                timeRange = binding.timeRangeInput.text.toString(),
                reminder = binding.reminderInput.text.toString(),
                note = binding.noteInput.text.toString()
            )
            val action = EditBadHabitFragmentDirections
                .actionEditBadHabitFragmentToViewBadHabitFragment(habitItem.id)
            findNavController().navigate(action)
        }
    }

    private fun isValidEntry() = viewModel.isValidEntry(
        binding.nameInput.text.toString()
    )

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu?.findItem(R.id.action_menu).isVisible = false
        menu?.setGroupVisible(R.id.menu_group, false)
    }

}