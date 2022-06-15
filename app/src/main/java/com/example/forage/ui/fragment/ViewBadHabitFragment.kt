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
import com.example.forage.databinding.FragmentViewBadHabitBinding
import com.example.forage.databinding.FragmentViewHabitBinding
import com.example.forage.model.BadHabitItem
import com.example.forage.model.HabitItem
import com.example.forage.ui.adapter.TutorialAdapter
import com.example.forage.ui.viewmodel.BadHabitViewModel
import com.example.forage.ui.viewmodel.BadHabitViewModelFactory
import com.example.forage.ui.viewmodel.HabitViewModel
import com.example.forage.ui.viewmodel.HabitViewModelFactory

class ViewBadHabitFragment : Fragment() {

    private val navigationArgs: ViewBadHabitFragmentArgs by navArgs()

    private var _binding: FragmentViewBadHabitBinding?= null
    private val binding get() = _binding!!

    private lateinit var habitItem: BadHabitItem

    private val viewModel: BadHabitViewModel by activityViewModels {
        BadHabitViewModelFactory(
            (activity?.application as BaseApplication).badHabitDatabase.badHabitDao()
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
        _binding = FragmentViewBadHabitBinding.inflate(inflater, container, false)
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

        viewModel.getBadHabitById(id).observe(this.viewLifecycleOwner) { selectedItem ->
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
            viewModel.getBadHabitById(id).observe(this.viewLifecycleOwner) { selectedItem ->
                habitItem = selectedItem
                bindHabitItem(habitItem)
            }
        //}

    }

    fun bindHabitItem(habitItem: BadHabitItem){
        binding.apply {

        }
    }

    fun deleteHabitItem(habitItem: BadHabitItem){
        viewModel.deleteBadHabit(habitItem)
        findNavController().navigate(R.id.action_viewBadHabitFragment_to_badHabitListFragment)
    }

    fun editHabitItem(habitItem: BadHabitItem){
        val action = ViewBadHabitFragmentDirections
            .actionViewBadHabitFragmentToEditBadHabitFragment(habitItem.id)
        findNavController().navigate(action)
    }

}