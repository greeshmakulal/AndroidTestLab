package com.example.androidtestlab.presentation

import com.example.androidtestlab.model.TaskItem

data class TaskUIState(
    val tasks: List<TaskItem> = emptyList(),
    val isLoading: Boolean = false,
    val isError: String? = null
) {
}