package com.example.forage.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.forage.model.VoiceDataItem

@Database(entities = [VoiceDataItem::class], version = 1, exportSchema = false)
abstract class VoiceDataDatabase : RoomDatabase() {
    abstract fun voiceDataDao(): VoiceDataDao

    companion object{
        @Volatile
        private var INSTANCE: VoiceDataDatabase ?= null
        fun getDatabase(context: Context): VoiceDataDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VoiceDataDatabase::class.java,
                    "voice_data_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}