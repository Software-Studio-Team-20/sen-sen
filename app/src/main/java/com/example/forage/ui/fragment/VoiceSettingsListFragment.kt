package com.example.forage.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.forage.BaseApplication
import com.example.forage.R
import com.example.forage.databinding.FragmentHabitListBinding
import com.example.forage.databinding.FragmentSettingsListBinding
import com.example.forage.databinding.FragmentVoiceSettingsListBinding
import com.example.forage.ui.adapter.HabitAdapter
import com.example.forage.ui.adapter.VoiceDataAdapter
import com.example.forage.ui.viewmodel.HabitViewModel
import com.example.forage.ui.viewmodel.HabitViewModelFactory
import com.example.forage.ui.viewmodel.VoiceDataViewModel
import com.example.forage.ui.viewmodel.VoiceDataViewModelFactory

class VoiceSettingsListFragment : Fragment() {
    private val viewModel: VoiceDataViewModel by activityViewModels {
        VoiceDataViewModelFactory(
            (activity?.application as BaseApplication).voiceDataDatabase.voiceDataDao()
        )
    }

    private var _binding: FragmentVoiceSettingsListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentVoiceSettingsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = VoiceDataAdapter { item ->
            val action = VoiceSettingsListFragmentDirections
                .actionVoiceSettingsListFragmentToEditVoicePackFragment()
            findNavController().navigate(action)
        }

        viewModel.allVoiceData.observe(this.viewLifecycleOwner){ item ->
            item.let {
                adapter.submitList(it)
            }
        }

        binding.apply {
            voiceSettingsRecyclerView.adapter = adapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu?.findItem(R.id.action_menu).isVisible = false
        menu?.findItem(R.id.menu_group).isVisible = false
    }
}