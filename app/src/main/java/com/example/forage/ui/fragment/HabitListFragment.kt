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
import com.example.forage.databinding.FragmentHabitListBinding
import com.example.forage.model.HabitItem
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
                .actionHabitListFragmentToAddHabitFragment()
            val dialogBuilder = AlertDialog.Builder(requireActivity())
            dialogBuilder.setMessage("Want to delet ${habitItem.name} ?")
                // if the dialog is cancelable
                .setCancelable(false)
                .setNegativeButton("No, let me think!", DialogInterface.OnClickListener {
                        dialog, id -> dialog.dismiss()
                })
                .setPositiveButton("Delete it!", DialogInterface.OnClickListener {
                        dialog, id -> viewModel.deleteHabit(habitItem)
                })

            val alert = dialogBuilder.create()
            alert.setTitle("Delete")
            alert.show()
//            findNavController().navigate(action)
        }

        viewModel.allHabit.observe(this.viewLifecycleOwner){ habitItem ->
            habitItem.let {
                adapter.submitList(it)
            }
        }

        binding.apply {
            habitRecyclerView.adapter = adapter

            /* On click add button, navigate to addHabitFragment */
            addButton.setOnClickListener {
                findNavController().navigate(
                    R.id.action_habitListFragment_to_addHabitFragment
                )
            }

            /* On click bad habit bookmark, navigate to badHabitListFragment */
            badBookmark.setOnClickListener {
                findNavController().navigate(
                    R.id.action_habitListFragment_to_badHabitListFragment
                )
            }

            /* On click edit button, show radio button */
            editButton.setOnClickListener {
                deleteHabit()
                var mediaPlayer = MediaPlayer.create(context, R.raw.good_delete)
                mediaPlayer.start()
            }

            /* On click char button, navigate to overviewFragment */
            chartButton.setOnClickListener{
                findNavController().navigate(
                    R.id.action_habitListFragment_to_overviewFragment
                )
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu?.findItem(R.id.menu_group).isVisible = false
    }

    private fun deleteHabit() {
        val allHabit : LiveData<List<HabitItem>> = viewModel.getHabit()
        val habit : List<HabitItem> = allHabit.getValue()!!
        for (i in habit.indices) {
            viewModel.deleteHabit(habit[i])
        }
    }
}