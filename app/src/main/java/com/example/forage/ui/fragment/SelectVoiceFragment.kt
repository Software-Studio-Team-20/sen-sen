package com.example.forage.ui.fragment

import android.media.RingtoneManager
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.forage.BaseApplication
import com.example.forage.R
import com.example.forage.databinding.FragmentAddVoicePackBinding
import com.example.forage.model.VoiceDataItem
import com.example.forage.ui.viewmodel.VoiceDataViewModel
import com.example.forage.ui.viewmodel.VoiceDataViewModelFactory
import xyz.aprildown.ultimateringtonepicker.RingtonePickerDialog
import xyz.aprildown.ultimateringtonepicker.UltimateRingtonePicker


class SelectVoiceFragment : Fragment() {
    private var _binding: FragmentAddVoicePackBinding? = null
    private val binding get() = _binding!!

    private val navigationArgs: AddVoicePackFragmentArgs by navArgs()
    private lateinit var voiceDataItem: VoiceDataItem

    private val viewModel: VoiceDataViewModel by activityViewModels() {
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

    private fun bindVoiceData(item: VoiceDataItem) {
        binding.apply {

        }
    }
}
