package com.example.forage.ui.viewmodel

import androidx.lifecycle.*
import com.example.forage.data.HabitDao
import com.example.forage.data.VoiceDataDao
import com.example.forage.model.HabitItem
import com.example.forage.model.VoiceDataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class VoiceDataViewModel (private val voiceDataDao: VoiceDataDao) : ViewModel() {
    val allVoiceData : LiveData<List<VoiceDataItem>> = voiceDataDao.getAll().asLiveData()

    fun receive (id : Long) : LiveData<VoiceDataItem> = voiceDataDao.gethabit(id).asLiveData()

    fun addVoiceData(
        name: String,
        goodStartURL: String,
        goodPauseURL: String,
        goodResumeURL: String,
        goodFinishURL: String,
        badStartURL: String,
        badPauseURL: String,
        badResumeURL: String,
        badFinishURL: String
    ){
        val item = VoiceDataItem(
            name = name,
            goodStartURL = goodStartURL,
            goodPauseURL = goodPauseURL,
            goodResumeURL = goodResumeURL,
            goodFinishURL = goodFinishURL,
            badStartURL = badStartURL,
            badPauseURL = badPauseURL,
            badResumeURL = badResumeURL,
            badFinishURL = badFinishURL
        )
        viewModelScope.launch {
            voiceDataDao.insert(item)
        }
    }

    fun updateVoiceData(
        id: Long,
        name: String,
        goodStartURL: String,
        goodPauseURL: String,
        goodResumeURL: String,
        goodFinishURL: String,
        badStartURL: String,
        badPauseURL: String,
        badResumeURL: String,
        badFinishURL: String
    ){
        val item = VoiceDataItem(
            id = id,
            name = name,
            goodStartURL = goodStartURL,
            goodPauseURL = goodPauseURL,
            goodResumeURL = goodResumeURL,
            goodFinishURL = goodFinishURL,
            badStartURL = badStartURL,
            badPauseURL = badPauseURL,
            badResumeURL = badResumeURL,
            badFinishURL = badFinishURL
        )
        viewModelScope.launch(Dispatchers.IO){
            voiceDataDao.update(item)
        }
    }

    fun deleteVoiceData(item: VoiceDataItem){
        viewModelScope.launch(Dispatchers.IO) {
            voiceDataDao.delete(item)
        }
    }

    fun isValidEntry(name: String): Boolean {
        return name.isNotBlank()
    }
}

class VoiceDataViewModelFactory(private val voiceDataDao: VoiceDataDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(VoiceDataViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VoiceDataViewModel(voiceDataDao) as T
        }
        throw IllegalArgumentException("unknown class")
    }
}