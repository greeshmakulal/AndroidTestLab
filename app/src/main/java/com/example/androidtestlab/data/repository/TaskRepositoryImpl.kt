package com.example.androidtestlab.data.repository

import com.example.androidtestlab.data.local.TaskDao
import com.example.androidtestlab.data.local.TaskEntity
import com.example.androidtestlab.model.TaskItem
import com.example.androidtestlab.model.toDomain
import com.example.androidtestlab.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(val taskDao: TaskDao): TaskRepository {
    override fun getAllTasks(): Flow<List<TaskItem>> {
        return taskDao.getAllTasks().map {entities ->entities.map{it.toDomain()} }
    }

    override suspend fun addTask(title: String) {
        taskDao.insertTask(TaskEntity(title = title))
    }

    override suspend fun updateTask(taskItem: TaskItem) {
       taskDao.updateTask(taskItem.toEntity().id)
    }

    override suspend fun deleteTask(id: Int) {
      taskDao.deleteTask(id)
    }
}