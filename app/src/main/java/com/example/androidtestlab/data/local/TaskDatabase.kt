package com.example.androidtestlab.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class TaskDatabase: RoomDatabase() {

    abstract fun getTaskDao():TaskDao
    companion object{
        @Volatile
        private var INSTANCE: TaskDatabase? = null
        fun getDataBase(context: Context): RoomDatabase {
            return INSTANCE ?: synchronized(this) { Room.databaseBuilder(
                context,
                TaskDatabase::class.java,
                "task_database"
            ).build().also { INSTANCE= it }
        }
    }
    }

}