package com.example.androidtestlab.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.androidtestlab.data.local.TaskDao
import com.example.androidtestlab.data.local.TaskDatabase
import com.example.androidtestlab.data.local.TaskEntity
import com.example.androidtestlab.model.TaskItem
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class TaskDaoTest {
    private lateinit var  database:TaskDatabase
    private lateinit var dao: TaskDao

    @Before
    fun setup()
    {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TaskDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.getTaskDao()

    }
    @After
    fun tearDown()
    {
        database.close()
    }

    @Test
    fun `insertTaskItem() should insert a task item into the database`()=runTest {
        dao.insertTask(TaskEntity(1,"complete training"))
    }
}