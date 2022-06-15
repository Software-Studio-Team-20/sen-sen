package com.example.forage.ui.viewmodel

import androidx.lifecycle.*
import com.example.forage.data.BadHabitDao
import com.example.forage.model.BadHabitItem
import com.example.forage.model.HabitItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class BadHabitViewModel (private val badHabitDao: BadHabitDao) : ViewModel() {
    val allBadHabit : LiveData<List<BadHabitItem>> = badHabitDao.getAll().asLiveData()

    fun receive (id : Long) : LiveData<BadHabitItem> = badHabitDao.gethabit(id).asLiveData()

    fun getBadHabit() : LiveData<List<BadHabitItem>> {
        return allBadHabit
    }

    fun getBadHabitById (id: Long) : LiveData<BadHabitItem> {
        return badHabitDao.gethabit(id).asLiveData()
    }

    fun addBadHabit(
        name: String,
        goal: String,
        frequency: String,
        timeRange: String,
        reminder: String,
        note: String
    ){
        val badHabitItem = BadHabitItem(
            name = name,
            goal = goal,
            frequency = frequency,
            timeRange = timeRange,
            reminderMesseage = reminder,
            note = note
        )
        viewModelScope.launch {
            badHabitDao.insert(badHabitItem)
        }
    }

    fun updateBadHabit(
        id: Long,
        name: String,
        goal: String,
        frequency: String,
        timeRange: String,
        reminder: String,
        note: String
    ){
        val badHabitItem = BadHabitItem(
            id = id,
            name = name,
            goal = goal,
            frequency = frequency,
            timeRange = timeRange,
            reminderMesseage = reminder,
            note = note
        )
        viewModelScope.launch(Dispatchers.IO){
            badHabitDao.update(badHabitItem)
        }
    }

    fun deleteBadHabit(badHabitItem: BadHabitItem){
        viewModelScope.launch(Dispatchers.IO) {
            badHabitDao.delete(badHabitItem)
        }
    }

    fun isValidEntry(name: String): Boolean {
        return name.isNotBlank()
    }
}

class BadHabitViewModelFactory(private val badHabitDao: BadHabitDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BadHabitViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BadHabitViewModel(badHabitDao) as T
        }
        throw IllegalArgumentException("unknown class")
    }
}