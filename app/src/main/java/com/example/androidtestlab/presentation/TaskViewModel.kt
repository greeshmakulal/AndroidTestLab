package com.example.androidtestlab.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtestlab.data.repository.TaskRepository
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

}