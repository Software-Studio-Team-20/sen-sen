package com.example.forage.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bad_habit_database")
data class BadHabitItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String?,
    val goal: String,
    val frequency: String,
    val timeRange: String,
    @ColumnInfo(name = "reminder_message")
    val reminderMesseage: String?,
    val note: String?
)