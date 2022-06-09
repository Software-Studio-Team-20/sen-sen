package com.example.forage.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habit_database")
data class HabitItem (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String?,
    val goal: Long,
    val frequency: Long,
    val timeRange: Long,
    @ColumnInfo(name = "reminder_message")
    val reminderMesseage: String?,
    val note: String?
)