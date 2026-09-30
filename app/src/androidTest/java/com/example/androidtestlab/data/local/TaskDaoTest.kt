package com.example.androidtestlab.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.androidtestlab.data.local.TaskDao
import com.example.androidtestlab.data.local.TaskDatabase
import com.example.androidtestlab.data.local.TaskEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
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
    fun addTaskItem()=runTest {
        dao.insertTask(TaskEntity(1,"complete training"))
        var result = dao.getAllTasks().first()
        Assert.assertEquals(1,result.size)
    }
    @Test
    fun getAllItems() = runTest{
        dao.insertTask(TaskEntity(1,"complete training"))
        dao.insertTask(TaskEntity(2,"Prepare for interview"))
        dao.insertTask(TaskEntity(title = "Prepare for dsa")) // checking without passing id since its auto generated one
        val result = dao.getAllTasks().first()


        Assert.assertEquals(3,result.size)
    }
}