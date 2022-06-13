package com.example.forage.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import com.example.forage.BaseApplication
import com.example.forage.R
import com.example.forage.databinding.FragmentOverviewBinding
import com.example.forage.model.BadHabitItem
import com.example.forage.ui.viewmodel.BadHabitViewModel
import com.example.forage.ui.viewmodel.BadHabitViewModelFactory
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate

data class Bad_Habit_Data (
    val name:String,
    val freq: Int,
)

class BadOverviewFragment: Fragment() {
    private var _binding: FragmentOverviewBinding?= null
    private val binding get() = _binding!!
    private lateinit var barChart: BarChart

    private var habitList = ArrayList<Bad_Habit_Data>()
    private val viewModel: BadHabitViewModel by activityViewModels(){
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
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)
        barChart = binding.barChart
        habitList = getBadHabitList()
        initBarChart()

        //now draw bar chart with dynamic data
        val entries: ArrayList<BarEntry> = ArrayList()

        //you can replace this data object with  your custom object
        for (i in habitList.indices) {
            val score = habitList[i]
            entries.add(BarEntry(i.toFloat(), score.freq.toFloat()))
        }

        val barDataSet = BarDataSet(entries, "")
        barDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)

        val data = BarData(barDataSet)
        barChart.data = data
        data.setDrawValues(false)

        barChart.invalidate()
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
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initBarChart() {
//        hide grid lines
        barChart.axisLeft.setDrawGridLines(false)
        val xAxis: XAxis = barChart.xAxis
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)

        //remove right y-axis
        barChart.axisRight.isEnabled = false

        //remove legend
        barChart.legend.isEnabled = false

        //remove description label
        barChart.description.isEnabled = false

        //add animation
        barChart.animateY(1000)

        // to draw label on xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = MyAxisFormatter()
        xAxis.setDrawLabels(true)
        xAxis.granularity = 1f
        xAxis.labelRotationAngle = +0f

    }

    inner class MyAxisFormatter : IndexAxisValueFormatter() {

        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            val index = value.toInt()
//            Log.d(TAG, "getAxisLabel: index $index")
            return if (index < habitList.size) {
                habitList[index].name
            } else {
                ""
            }
        }
    }

    // simulate api call
    // we are initialising it directly
    private fun getBadHabitList(): ArrayList<Bad_Habit_Data> {
        val allHabit : LiveData<List<BadHabitItem>> = viewModel.getHabit()
        val habit : List<BadHabitItem> = allHabit.getValue()!!
        for (i in habit.indices) {
            habitList.add(Bad_Habit_Data(habit[i].name.toString(), habit[i].frequency.toInt()))
        }

        return habitList
    }
}