package com.example.forage.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.forage.R
import com.example.forage.databinding.FragmentSettingsListBinding
import com.example.forage.ui.adapter.SettingsAdapter
import com.example.forage.ui.adapter.VoiceDataAdapter

class SettingsListFragment : Fragment() {
    private var _binding: FragmentSettingsListBinding ?= null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsListBinding.inflate(inflater, container, false)
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
        recyclerView = binding.settingsRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        val adapter = SettingsAdapter { item ->
            val action = SettingsListFragmentDirections.actionSettingsListFragmentToVoiceSettingsListFragment()
            findNavController().navigate(action)
        }

        recyclerView.adapter = adapter
        binding.apply {

        }
    }
}