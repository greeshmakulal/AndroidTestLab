package com.example.androidtestlab.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    val title:String = "",
    var isCompleted:Boolean = false,
) {
}