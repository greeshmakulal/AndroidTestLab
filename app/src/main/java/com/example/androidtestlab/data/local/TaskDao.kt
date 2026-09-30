package com.example.androidtestlab.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: TaskEntity)
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<TaskEntity>>
    @Query("DELETE FROM tasks WHERE id = :taskId")
    suspend fun deleteTask(taskId: Int)
    @Query("UPDATE tasks SET isCompleted = NOT isCompleted WHERE id = :taskId")
    suspend fun updateTask(taskId: Int)
}
