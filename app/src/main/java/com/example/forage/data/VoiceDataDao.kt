package com.example.forage.data

import androidx.room.*
import com.example.forage.model.BadHabitItem
import com.example.forage.model.VoiceDataItem
import kotlinx.coroutines.flow.Flow

@Dao
interface VoiceDataDao {
    @Query("SELECT * from voice_data_database")
    fun getAll(): Flow<List<VoiceDataItem>>

    @Query("SELECT * from voice_data_database WHERE id = :id")
    fun gethabit(id: Long): Flow<VoiceDataItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: VoiceDataItem)

    @Update
    suspend fun update(item: VoiceDataItem)

    @Delete
    suspend fun delete(item: VoiceDataItem)
}