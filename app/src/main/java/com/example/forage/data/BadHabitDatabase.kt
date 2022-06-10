package com.example.forage.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.forage.model.BadHabitItem

@Database(entities = [BadHabitItem::class], version = 1, exportSchema = false)
abstract class BadHabitDatabase : RoomDatabase() {
    abstract fun badHabitDao(): BadHabitDao

    companion object{
        @Volatile
        private var INSTANCE: BadHabitDatabase ?= null
        fun getDatabase(context: Context): BadHabitDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BadHabitDatabase::class.java,
                    "bad_habit_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }

        }
    }
}