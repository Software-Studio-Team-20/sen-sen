package com.example.forage.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.forage.R
import com.example.forage.databinding.FragmentAddVoicePackBinding

class AddVoicePackFragment : Fragment() {
    private var _binding: FragmentAddVoicePackBinding?= null
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
        binding.apply{
            voicePackAddButton.setOnClickListener {
                findNavController().navigate(R.id.action_addVoicePackFragment_to_voiceSettingsListFragment)
            }
            voicePackCancelButton.setOnClickListener {
                findNavController().navigate(R.id.action_addVoicePackFragment_to_voiceSettingsListFragment)
            }
        }
    }
}
