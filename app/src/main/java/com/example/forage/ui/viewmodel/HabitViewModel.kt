package com.example.forage.ui.viewmodel

import androidx.lifecycle.*
import com.example.forage.data.HabitDao
import com.example.forage.model.HabitItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class HabitViewModel(private val habitDao: HabitDao) : ViewModel() {
    val allHabit : LiveData<List<HabitItem>> = habitDao.getAll().asLiveData()

    fun receive (id : Long) : LiveData<HabitItem> = habitDao.gethabit(id).asLiveData()

    fun getHabit() : LiveData<List<HabitItem>> {
        return allHabit
    }

    fun getHabitById (id: Long) : LiveData<HabitItem> {
        return habitDao.gethabit(id).asLiveData()
    }

    fun addHabit(
        name: String,
        goal: String,
        frequency: String,
        timeRange: String,
        reminder: String,
        note: String
    ){
        val habitItem = HabitItem(
            name = name,
            goal = goal,
            frequency = frequency,
            timeRange = timeRange,
            reminderMesseage = reminder,
            note = note
        )
        viewModelScope.launch {
            habitDao.insert(habitItem)
        }
    }

    fun updateHabit(
        id: Long,
        name: String,
        goal: String,
        frequency: String,
        timeRange: String,
        reminder: String,
        note: String
    ){
        val habitItem = HabitItem(
            id = id,
            name = name,
            goal = goal,
            frequency = frequency,
            timeRange = timeRange,
            reminderMesseage = reminder,
            note = note
        )
        viewModelScope.launch(Dispatchers.IO){
            habitDao.update(habitItem)
        }
    }

    fun deleteHabit(habitItem: HabitItem){
        viewModelScope.launch(Dispatchers.IO) {
            habitDao.delete(habitItem)
        }
    }

    fun isValidEntry(name: String): Boolean {
        return name.isNotBlank()
    }
}

class HabitViewModelFactory(private val habitDao: HabitDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HabitViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HabitViewModel(habitDao) as T
        }
        throw IllegalArgumentException("unknown class")
    }
}