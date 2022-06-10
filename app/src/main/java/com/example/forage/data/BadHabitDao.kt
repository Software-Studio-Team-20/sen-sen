package com.example.forage.data

import androidx.room.*
import com.example.forage.model.BadHabitItem
import kotlinx.coroutines.flow.Flow

@Dao
interface BadHabitDao {
    @Query("SELECT * from bad_habit_database")
    fun getAll(): Flow<List<BadHabitItem>>

    @Query("SELECT * from bad_habit_database WHERE id = :id")
    fun gethabit(id: Long): Flow<BadHabitItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(habitItem: BadHabitItem)

    @Update
    suspend fun update(habitItem: BadHabitItem)

    @Delete
    suspend fun delete(habitItem: BadHabitItem)
}