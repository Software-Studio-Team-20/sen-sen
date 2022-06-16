package com.example.forage.ui.fragment

import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.forage.BaseApplication
import com.example.forage.R
import com.example.forage.databinding.FragmentAddVoicePackBinding
import com.example.forage.model.VoiceDataItem
import com.example.forage.ui.viewmodel.VoiceDataViewModel
import com.example.forage.ui.viewmodel.VoiceDataViewModelFactory
import xyz.aprildown.ultimateringtonepicker.RingtonePickerDialog
import xyz.aprildown.ultimateringtonepicker.UltimateRingtonePicker

val settings = UltimateRingtonePicker.Settings(
    systemRingtonePicker = UltimateRingtonePicker.SystemRingtonePicker(
        customSection = UltimateRingtonePicker.SystemRingtonePicker.CustomSection(),
        defaultSection = UltimateRingtonePicker.SystemRingtonePicker.DefaultSection(),
        ringtoneTypes = listOf(
            RingtoneManager.TYPE_RINGTONE,
            RingtoneManager.TYPE_NOTIFICATION,
            RingtoneManager.TYPE_ALARM
        )
    ),
    deviceRingtonePicker = UltimateRingtonePicker.DeviceRingtonePicker(
        deviceRingtoneTypes = listOf(
            UltimateRingtonePicker.RingtoneCategoryType.All,
            UltimateRingtonePicker.RingtoneCategoryType.Artist,
            UltimateRingtonePicker.RingtoneCategoryType.Album,
            UltimateRingtonePicker.RingtoneCategoryType.Folder
        )
    )
)

class AddVoicePackFragment : Fragment() {
    private var _binding: FragmentAddVoicePackBinding?= null
    private val binding get() = _binding!!

    private val navigationArgs: AddVoicePackFragmentArgs by navArgs()
    private lateinit var voiceDataItem: VoiceDataItem

    private val viewModel: VoiceDataViewModel by activityViewModels(){
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
        _binding = FragmentAddVoicePackBinding.inflate(inflater, container, false)
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
            viewModel.receive(id).observe(this.viewLifecycleOwner) { item ->
                voiceDataItem = item
                bindVoiceData(voiceDataItem)
            }
        } else {
            binding.voicePackAddButton.setOnClickListener {
                addVoiceData()
            }
        }
        binding.apply {
            /* On click add button, navigate to addHabitFragment */
            selectVoice1.setOnClickListener {
                RingtonePickerDialog.createInstance(
                    settings = settings,
                    dialogTitle = "Dialog!"
                ).show(childFragmentManager, null)
            }
            selectVoice2.setOnClickListener {
                RingtonePickerDialog.createInstance(
                    settings = settings,
                    dialogTitle = "Dialog!"
                ).show(childFragmentManager, null)
            }
            selectVoice3.setOnClickListener {
                RingtonePickerDialog.createInstance(
                    settings = settings,
                    dialogTitle = "Dialog!"
                ).show(childFragmentManager, null)
            }
            selectVoice4.setOnClickListener {
                RingtonePickerDialog.createInstance(
                    settings = settings,
                    dialogTitle = "Dialog!"
                ).show(childFragmentManager, null)
            }
        }
    }


//    fun selectVoice(){
//        RingtonePickerDialog.createEphemeralInstance(
//            settings = settings,
//            dialogTitle = "Dialog",
//            listener = object : UltimateRingtonePicker.RingtonePickerListener {
//                override fun onRingtonePicked(ringtones: List<UltimateRingtonePicker.RingtoneEntry>) {
//
//                }
//            }
//        ).show(supportFragmentManager, null)
//    }

    private fun addVoiceData() {
        /*if (isValidEntry()) {
            /*viewModel.addVoiceData(

            )
            findNavController().navigate(
                R.id.action_addVoicePackFragment_to_voiceSettingsListFragment
            )*/
        }*/
    }

    private fun bindVoiceData(item: VoiceDataItem) {
        binding.apply {

        }
    }

    /*private fun isValidEntry() = viewModel.isValidEntry(
        binding..text.toString()
    )*/
}
