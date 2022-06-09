package com.example.forage.data

import androidx.room.*
import com.example.forage.model.HabitItem
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Query("SELECT * from habit_database")
    fun getAll(): Flow<List<HabitItem>>

    @Query("SELECT * from habit_database WHERE id = :id")
    fun gethabit(id: Long): Flow<HabitItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(habitItem: HabitItem)

    @Update
    suspend fun update(habitItem: HabitItem)

    @Delete
    suspend fun delete(habitItem: HabitItem)
}