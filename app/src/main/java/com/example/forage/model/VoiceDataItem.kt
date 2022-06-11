package com.example.forage.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "voice_data_database")
data class VoiceDataItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String?,
    val goodStartURL: String?,
    val goodPauseURL: String?,
    val goodResumeURL: String?,
    val goodFinishURL: String?,
    val badStartURL: String?,
    val badPauseURL: String?,
    val badResumeURL: String?,
    val badFinishURL: String?
)