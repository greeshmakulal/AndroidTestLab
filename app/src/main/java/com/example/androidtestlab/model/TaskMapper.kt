package com.example.androidtestlab.model

import com.example.androidtestlab.data.local.TaskEntity

    fun TaskEntity.toDomain(): TaskItem {
        return TaskItem(
            id = id,
            title = title,
            isCompleted = isCompleted
        )
    }
    fun TaskItem.toEntity(): TaskEntity {
        return TaskEntity(
            id = id,
            title = title,
            isCompleted = isCompleted
        )
    }
