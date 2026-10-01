package com.example.androidtestlab.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import com.example.androidtestlab.data.repository.TaskRepository
import com.example.androidtestlab.model.TaskItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository): ViewModel()
{
    val _uiState = MutableStateFlow(TaskUIState())
    val uiState: StateFlow<TaskUIState> = _uiState.asStateFlow()

    init {
        observeTasks()
    }

    fun observeTasks() {
        viewModelScope.launch {
            repository.getAllTasks().collect{tasks ->
                _uiState.value = _uiState.value.copy(
                    tasks = tasks,
                    isLoading = false,
                    isError = null
                )
            }

        }
    }

    fun addTask(title: String)
    {
        viewModelScope.launch {
            repository.addTask(title)
        }
    }
    fun deleteTask(taskId: Int)
    {
        viewModelScope.launch {
            repository.deleteTask(taskId)
        }
    }
    fun toggleTask(task: TaskItem) {
        viewModelScope.launch {
            repository.updateTask(
                task.copy(
                    isCompleted = !task.isCompleted
                )
            )
        }
    }
}