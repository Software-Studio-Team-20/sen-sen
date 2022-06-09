package com.example.forage.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.forage.model.HabitItem

@Database(entities = [HabitItem::class], version = 1, exportSchema = false)
abstract class HabitDatabase :RoomDatabase() {
    abstract fun habitDao(): HabitDao

    companion object{
        @Volatile
        private var INSTANCE: HabitDatabase ?= null
        fun getDatabase(context: Context): HabitDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HabitDatabase::class.java,
                    "habit_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }

        }
    }
}