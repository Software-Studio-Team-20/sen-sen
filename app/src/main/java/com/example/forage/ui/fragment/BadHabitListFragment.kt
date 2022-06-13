package com.example.forage.ui.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.media.MediaPlayer
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import com.example.forage.BaseApplication
import com.example.forage.R
import com.example.forage.databinding.FragmentBadHabitListBinding
import com.example.forage.model.BadHabitItem
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
                .actionBadHabitListFragmentToAddBadHabitFragment()
            val dialogBuilder = AlertDialog.Builder(requireActivity())
            dialogBuilder.setMessage("Want to delet ${habitItem.name} ?")
                // if the dialog is cancelable
                .setCancelable(false)
                .setNegativeButton("No, let me think!", DialogInterface.OnClickListener {
                        dialog, id -> dialog.dismiss()
                })
                .setPositiveButton("Delete it!", DialogInterface.OnClickListener {
                        dialog, id -> viewModel.deleteBadHabit(habitItem)
                })

            val alert = dialogBuilder.create()
            alert.setTitle("Delete")
            alert.show()
//            findNavController().navigate(action)
        }

        viewModel.allHabit.observe(this.viewLifecycleOwner) { habitItem ->
            habitItem.let {
                adapter.submitList(it)
            }
        }

        binding.apply {
            habitRecyclerView.adapter = adapter

            /* On click add button, navigate to addBadHabitFragment */
            addButton.setOnClickListener {
                findNavController().navigate(
                    R.id.action_badHabitListFragment_to_addBadHabitFragment
                )
            }

            /* On click good habit bookmark, navigate to habitListFragment */
            goodBookmark.setOnClickListener {
                findNavController().navigate(
                    R.id.action_badHabitListFragment_to_habitListFragment
                )
            }

            /* On click edit button, show radio button */
            editButton.setOnClickListener {
                deleteHabit()
                var mediaPlayer = MediaPlayer.create(context, R.raw.bad_delete)
                mediaPlayer.start()
            }

            /* On click char button, navigate to badOverviewFragment */
            chartButton.setOnClickListener {
                findNavController().navigate(
                    R.id.action_badHabitListFragment_to_badOverviewFragment
                )
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu?.findItem(R.id.menu_group).isVisible = false
    }

    private fun deleteHabit() {
        val allHabit : LiveData<List<BadHabitItem>> = viewModel.getHabit()
        val habit : List<BadHabitItem> = allHabit.getValue()!!
        for (i in habit.indices) {
            viewModel.deleteBadHabit(habit[i])
        }
    }
}