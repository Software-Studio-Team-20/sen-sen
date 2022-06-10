package com.example.forage.ui.viewmodel

import androidx.lifecycle.*
import com.example.forage.data.BadHabitDao
import com.example.forage.model.BadHabitItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class BadHabitViewModel (private val habitDao: BadHabitDao) : ViewModel() {
    val allHabit : LiveData<List<BadHabitItem>> = habitDao.getAll().asLiveData()

    fun receive (id : Long) : LiveData<BadHabitItem> = habitDao.gethabit(id).asLiveData()

    fun addHabit(
        name: String,
        goal: String,
        frequency: String,
        timeRange: String,
        reminder: String,
        note: String
    ){
        val habitItem = BadHabitItem(
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
        val habitItem = BadHabitItem(
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

    fun deleteHabit(habitItem: BadHabitItem){
        viewModelScope.launch(Dispatchers.IO) {
            habitDao.delete(habitItem)
        }
    }

    fun isValidEntry(name: String): Boolean {
        return name.isNotBlank()
    }
}

class BadHabitViewModelFactory(private val habitDao: BadHabitDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BadHabitViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BadHabitViewModel(habitDao) as T
        }
        throw IllegalArgumentException("unknown class")
    }
}