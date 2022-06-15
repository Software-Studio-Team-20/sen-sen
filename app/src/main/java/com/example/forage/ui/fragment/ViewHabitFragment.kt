package com.example.forage.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.forage.BaseApplication
import com.example.forage.R
import com.example.forage.databinding.FragmentTutorialBinding
import com.example.forage.databinding.FragmentViewHabitBinding
import com.example.forage.model.HabitItem
import com.example.forage.ui.adapter.TutorialAdapter
import com.example.forage.ui.viewmodel.HabitViewModel
import com.example.forage.ui.viewmodel.HabitViewModelFactory

class ViewHabitFragment : Fragment() {

    private val navigationArgs: ViewHabitFragmentArgs by navArgs()

    private var _binding: FragmentViewHabitBinding?= null
    private val binding get() = _binding!!

    private lateinit var habitItem: HabitItem

    private val viewModel: HabitViewModel by activityViewModels {
        HabitViewModelFactory(
            (activity?.application as BaseApplication).habitDatabase.habitDao()
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
        _binding = FragmentViewHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu?.findItem(R.id.action_menu).isVisible = false
        menu.setGroupVisible(R.id.menu_group,true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = navigationArgs.id

        viewModel.getHabitById(id).observe(this.viewLifecycleOwner) { selectedItem ->
            habitItem = selectedItem
        }

        return when(item.itemId){
            R.id.menu_group_delete -> {
                deleteHabitItem(habitItem)
                true
            }

            R.id.menu_group_archive -> {
                true
            }

            R.id.menu_group_edit -> {
                editHabitItem(habitItem)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.id
        //if(id>0) {
            viewModel.getHabitById(id).observe(this.viewLifecycleOwner) { selectedItem ->
                habitItem = selectedItem
                bindHabitItem(habitItem)
            }
        //}

    }

    fun bindHabitItem(habitItem: HabitItem){
        binding.apply {

        }
    }

    fun deleteHabitItem(habitItem: HabitItem){
        viewModel.deleteHabit(habitItem)
        findNavController().navigate(R.id.action_viewHabitFragment_to_habitListFragment)
    }

    fun editHabitItem(habitItem: HabitItem){
        val action = ViewHabitFragmentDirections
            .actionViewHabitFragmentToEditHabitFragment(habitItem.id)
        findNavController().navigate(action)
    }

}