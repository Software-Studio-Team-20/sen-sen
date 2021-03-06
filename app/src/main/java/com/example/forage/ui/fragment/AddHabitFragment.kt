package com.example.forage.ui.fragment

import android.media.MediaPlayer
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.forage.BaseApplication
import com.example.forage.MainActivity.Companion.INDEX
import com.example.forage.R
import com.example.forage.databinding.FragmentAddHabitBinding
import com.example.forage.model.HabitItem
import com.example.forage.model.VoiceDataItem
import com.example.forage.ui.viewmodel.HabitViewModel
import com.example.forage.ui.viewmodel.HabitViewModelFactory
import com.example.forage.ui.viewmodel.VoiceDataViewModel
import com.example.forage.ui.viewmodel.VoiceDataViewModelFactory

class AddHabitFragment: Fragment() {
    private var _binding: FragmentAddHabitBinding?= null
    private val binding get() = _binding!!

    private val navigationArgs: AddHabitFragmentArgs by navArgs()
    private lateinit var habitItem: HabitItem

    private val viewModel: HabitViewModel by activityViewModels(){
        HabitViewModelFactory(
            (activity?.application as BaseApplication).habitDatabase.habitDao()
        )
    }

    private val viewModel_voice: VoiceDataViewModel by activityViewModels(){
        VoiceDataViewModelFactory(
            (activity?.application as BaseApplication).voiceDataDatabase.voiceDataDao()
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
        _binding = FragmentAddHabitBinding.inflate(inflater, container, false)
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

        var voiceDataItem : VoiceDataItem?= null
            viewModel_voice.receive(INDEX).observe(this.viewLifecycleOwner) { selectedItem ->
                voiceDataItem = selectedItem
        }

        if (id > 0) {
            viewModel.receive(id).observe(this.viewLifecycleOwner) { pointhabits ->
                habitItem = pointhabits
                bindHabit(habitItem)
            }
        } else {
            binding.saveBtn.setOnClickListener {
                addHabit()
                System.out.println("lalala")
                //System.out.println(voiceDataItem?.goodPauseURL!!.toUri())
                if(INDEX < 2) {
                    var mediaPlayer = MediaPlayer.create(context, R.raw.good_new)
                    mediaPlayer.start()
//                    mediaPlayer.release()
                }else{
                    var mediaPlayer = MediaPlayer.create(context, voiceDataItem?.goodPauseURL!!.toUri())
                    mediaPlayer.start()
//                    mediaPlayer.release()
                }
            }
        }
    }

    private fun addHabit() {
        if (isValidEntry()) {
            viewModel.addHabit(
                binding.nameInput.text.toString(),
                binding.goalsInput.text.toString(),
                binding.frequencyInput.text.toString(),
                binding.timeRangeInput.text.toString(),
                binding.reminderInput.text.toString(),
                binding.noteInput.text.toString()
            )
            findNavController().navigate(
                R.id.action_addHabitFragment_to_habitListFragment
            )
        }
        else{
            val toast = Toast.makeText(context, "need to type name, frequency ,and goals.\nfrequency and goals can only be numbers.", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER,0,0)
            toast.show()
        }
    }
    private fun updateHabit() {
        if (isValidEntry()) {
            viewModel.updateHabit(
                id = navigationArgs.id,
                name = binding.nameInput.text.toString(),
                goal = binding.goalsInput.text.toString(),
                frequency = binding.frequencyInput.text.toString(),
                timeRange = binding.timeRangeInput.text.toString(),
                reminder = binding.reminderInput.text.toString(),
                note = binding.noteInput.text.toString()
            )
            findNavController().navigate(
                R.id.action_addHabitFragment_to_habitListFragment
            )
        }
        else{
            val toast = Toast.makeText(context, "need to type name, frequency ,and goals.\nfrequency and goals can only be numbers.", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER,0,0)
            toast.show()
        }
    }

    private fun bindHabit(habitItem: HabitItem) {
        binding.apply {
            nameInput.setText(habitItem.name, TextView.BufferType.SPANNABLE)
            goalsInput.setText(habitItem.goal, TextView.BufferType.SPANNABLE)
            frequencyInput.setText(habitItem.frequency, TextView.BufferType.SPANNABLE)
            timeRangeInput.setText(habitItem.timeRange, TextView.BufferType.SPANNABLE)
            reminderInput.setText(habitItem.reminderMesseage, TextView.BufferType.SPANNABLE)
            noteInput.setText(habitItem.note, TextView.BufferType.SPANNABLE)
            saveBtn.setOnClickListener {
                updateHabit()
            }
        }
    }

    private fun isValidEntry() = viewModel.isValidEntry(
        binding.nameInput.text.toString(),
        binding.goalsInput.text.toString(),
        binding.frequencyInput.toString()
    )
}