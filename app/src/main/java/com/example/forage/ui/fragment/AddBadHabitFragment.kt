package com.example.forage.ui.fragment

import android.media.MediaPlayer
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.forage.BaseApplication
import com.example.forage.R
import com.example.forage.databinding.FragmentAddBadHabitBinding
import com.example.forage.model.BadHabitItem
import com.example.forage.ui.viewmodel.BadHabitViewModel
import com.example.forage.ui.viewmodel.BadHabitViewModelFactory

class AddBadHabitFragment: Fragment() {
    private var _binding: FragmentAddBadHabitBinding?= null
    private val binding get() = _binding!!

    private val navigationArgs: AddBadHabitFragmentArgs by navArgs()
    private lateinit var badHabitItem: BadHabitItem

    var mediaPlayer = MediaPlayer.create(context, R.raw.bad_new)

    private val viewModel: BadHabitViewModel by activityViewModels(){
        BadHabitViewModelFactory(
            (activity?.application as BaseApplication).badHabitDatabase.badHabitDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBadHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu?.findItem(R.id.action_menu).isVisible = false
        menu.setGroupVisible(R.id.menu_group, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.id
        if (id > 0) {
            viewModel.receive(id).observe(this.viewLifecycleOwner) { pointbadhabits ->
                badHabitItem = pointbadhabits
                bindBadHabit(badHabitItem)
            }
        } else {
            binding.saveBtn.setOnClickListener {
                addBadHabit()
                mediaPlayer.start()
            }
        }
    }

    private fun addBadHabit() {
        if (isValidEntry()) {
            viewModel.addBadHabit(
                binding.nameInput.text.toString(),
                binding.goalsInput.text.toString(),
                binding.frequencyInput.text.toString(),
                binding.timeRangeInput.text.toString(),
                binding.reminderInput.text.toString(),
                binding.noteInput.text.toString()
            )
            findNavController().navigate(
                R.id.action_addBadHabitFragment_to_badHabitListFragment
            )
        }
    }

    private fun updateBadHabit() {
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
            findNavController().navigate(
                R.id.action_addBadHabitFragment_to_badHabitListFragment
            )
        }
    }

    private fun bindBadHabit(badHabitItem: BadHabitItem) {
        binding.apply {
            nameInput.setText(badHabitItem.name, TextView.BufferType.SPANNABLE)
            goalsInput.setText(badHabitItem.goal, TextView.BufferType.SPANNABLE)
            frequencyInput.setText(badHabitItem.frequency, TextView.BufferType.SPANNABLE)
            timeRangeInput.setText(badHabitItem.timeRange, TextView.BufferType.SPANNABLE)
            reminderInput.setText(badHabitItem.reminderMesseage, TextView.BufferType.SPANNABLE)
            noteInput.setText(badHabitItem.note, TextView.BufferType.SPANNABLE)
            saveBtn.setOnClickListener {
                updateBadHabit()
            }
        }
    }

    private fun isValidEntry() = viewModel.isValidEntry(
        binding.nameInput.text.toString()
    )
}