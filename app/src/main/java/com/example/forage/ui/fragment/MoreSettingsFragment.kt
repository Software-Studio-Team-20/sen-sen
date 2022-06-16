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
import com.example.forage.databinding.FragmentEditVoicePackBinding
import com.example.forage.databinding.FragmentMoreSettingsBinding
import com.example.forage.model.VoiceDataItem
import com.example.forage.ui.viewmodel.VoiceDataViewModel
import com.example.forage.ui.viewmodel.VoiceDataViewModelFactory

class MoreSettingsFragment : Fragment() {
    private var _binding: FragmentMoreSettingsBinding?= null
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
        _binding = FragmentMoreSettingsBinding.inflate(inflater, container, false)
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
        binding.apply {
            startOpt1.setOnClickListener {
                startOpt1.visibility = TextView.GONE
                startOpt2.visibility = TextView.VISIBLE
            }

            startOpt2.setOnClickListener {
                startOpt1.visibility = TextView.VISIBLE
                startOpt2.visibility = TextView.GONE
            }

            positionOpt1.setOnClickListener {
                positionOpt1.visibility = TextView.GONE
                positionOpt2.visibility = TextView.VISIBLE
            }

            positionOpt2.setOnClickListener {
                positionOpt1.visibility = TextView.VISIBLE
                positionOpt2.visibility = TextView.GONE
            }

            modeOpt1.setOnClickListener {
                modeOpt1.visibility = TextView.GONE
                modeOpt2.visibility = TextView.VISIBLE
            }

            modeOpt2.setOnClickListener {
                modeOpt1.visibility = TextView.VISIBLE
                modeOpt2.visibility = TextView.GONE
            }
        }
    }
}