package com.accme.timepad.persistence

import androidx.room.*
import com.accme.timepad.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(task: Task)

    @Query("SELECT * FROM tasks ORDER by date ASC")
    fun all(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE id=:id")
    fun getById(id: Int): Flow<Task>

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)
}
