package com.example.androidtestlab

import com.example.androidtestlab.data.repository.TaskRepository
import com.example.androidtestlab.model.TaskItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeRepository: TaskRepository {
    private val tasks = MutableStateFlow<List<TaskItem>>(emptyList())

    fun setTasks(newTasks: List<TaskItem>) {
        tasks.value = newTasks
    }
    override fun getAllTasks(): Flow<List<TaskItem>> {
            return  tasks
    }

    override suspend fun addTask(title: String) {
        val newTaskItem = TaskItem(
            id = tasks.value.size + 1,
            title = title
        )

        tasks.value += newTaskItem
    }

    override suspend fun updateTask(taskItem: TaskItem) {
        tasks.value = tasks.value.map {
            if (it.id == taskItem.id) {
                taskItem
            } else {
                it
            }
        }
    }

    override suspend fun deleteTask(id: Int) {
        tasks.value = tasks.value.filter {
            it.id != id
        }
    }
}