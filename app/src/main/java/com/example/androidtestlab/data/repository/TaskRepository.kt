package com.example.androidtestlab.data.repository

import com.example.androidtestlab.model.TaskItem
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getAllTasks(): Flow<List<TaskItem>>

    suspend fun addTask(title: String)

    suspend fun updateTask(taskItem: TaskItem)

    suspend fun deleteTask(id: Int)

}