package com.example.forage.ui.fragment

import android.graphics.*
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
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

        //drawCurrent((habitItem.frequency.toFloat()/habitItem.goal.toFloat())*360)

            binding.addFrequency.setOnClickListener(){
                if(habitItem.frequency.toInt() < habitItem.goal.toInt()) {
                    var frequencyTemp = habitItem.frequency
                    frequencyTemp = (frequencyTemp.toInt() + 1).toString()
                    changeFrequency(habitItem, frequencyTemp)
                    //var progressAngleTemp = (frequencyTemp.toFloat()/habitItem.goal.toFloat())*360
                    //drawCurrent(progressAngleTemp)
                }
            }

            binding.resetFrequency.setOnClickListener(){
                changeFrequency(habitItem, "0")
                //drawCurrent(0f)
            }
        //}


    }

    fun bindHabitItem(habitItem: HabitItem){
        binding.apply {
            currentFrequency.text = habitItem.frequency
            currentGoal.text = habitItem.goal
            drawCurrent((habitItem.frequency.toFloat()/habitItem.goal.toFloat())*360)
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

    fun changeFrequency(habitItem:HabitItem, tmp:String){
        viewModel.updateHabit(
            id = habitItem.id,
            name = habitItem.name.toString(),
            goal = habitItem.goal,
            frequency = tmp,
            timeRange = habitItem.timeRange,
            reminder = habitItem.reminderMesseage.toString(),
            note = habitItem.note.toString()
        )
    }



    fun drawCurrent(progressAngle : Float){
        var bitmap = Bitmap.createBitmap(250, 250, Bitmap.Config.ARGB_8888);
        var canvas = Canvas(bitmap);
        var paint = Paint(Paint.ANTI_ALIAS_FLAG);
        paint.style = Paint.Style.STROKE
        paint.setStrokeWidth(15f)
        paint.setColor(getResources().getColor(R.color.orange))
        var path = Path()
        var circleSize = RectF()

        circleSize.set(25f,25f,225f,225f)
        path.addArc(circleSize,0f,progressAngle)
        canvas.drawPath(path,paint)

        binding.imageView.setImageBitmap(bitmap);//var progress = Path()

        /*
        paint.setColor(Color.BLACK);
        canvas.drawCircle(0f,0f, 50f, paint)
        binding.imageView.setImageBitmap(bitmap);//var progress = Path()
        */
    }


}