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
import com.example.forage.databinding.FragmentBadHabitListBinding
import com.example.forage.ui.adapter.BadHabitAdapter
import com.example.forage.ui.viewmodel.BadHabitViewModel
import com.example.forage.ui.viewmodel.BadHabitViewModelFactory

class BadHabitListFragment : Fragment() {
    private val viewModel: BadHabitViewModel by activityViewModels {
        BadHabitViewModelFactory(
            (activity?.application as BaseApplication).badHabitDatabase.badHabitDao()
        )
    }

    private var _binding: FragmentBadHabitListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBadHabitListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = BadHabitAdapter { habitItem ->
            val action = BadHabitListFragmentDirections
                .actionBadHabitListFragmentToAddBadHabitFragment(habitItem.id)
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
                    R.id.action_badHabitListFragment_to_addBadHabitFragment
                )
            }
            goodBookmark.setOnClickListener {
                findNavController().navigate(
                    R.id.action_badHabitListFragment_to_habitListFragment
                )
            }
        }
    }
}