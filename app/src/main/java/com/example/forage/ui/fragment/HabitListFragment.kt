package com.example.forage.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.forage.BaseApplication
import com.example.forage.R
import com.example.forage.databinding.FragmentHabitListBinding
import com.example.forage.ui.adapter.HabitAdapter
import com.example.forage.ui.viewmodel.HabitViewModel
import com.example.forage.ui.viewmodel.HabitViewModelFactory

class HabitListFragment : Fragment() {
    private val viewModel: HabitViewModel by activityViewModels {
        HabitViewModelFactory(
            (activity?.application as BaseApplication).habitDatabase.habitDao()
        )
    }

    private var _binding: FragmentHabitListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHabitListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HabitAdapter { habitItem ->
            val action = HabitListFragmentDirections
                .actionHabitListFragmentToAddHabitFragment(habitItem.id)
            findNavController().navigate(action)
        }

        viewModel.allHabit.observe(this.viewLifecycleOwner){ habitItem ->
            habitItem.let {
                adapter.submitList(it)
            }
        }

        binding.apply {
            habitRecyclerView.adapter = adapter
            addButton.setOnClickListener {
                findNavController().navigate(
                    R.id.action_habitListFragment_to_addHabitFragment
                )
            }
            badBookmark.setOnClickListener {
                findNavController().navigate(
                    R.id.action_habitListFragment_to_badHabitListFragment
                )
            }
        }
    }
}