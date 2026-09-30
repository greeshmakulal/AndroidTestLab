package com.example.androidtestlab

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class FakeRepositoryTest {
    val repository = FakeRepository()
    @Test
    fun `addTasks add task`()= runTest {
        repository.addTask("task 7")
        val tasks = repository.getAllTasks().first()
        assertEquals(1, tasks.size)
        assertEquals("task 7", tasks[0].title)
    }
    @Test
    fun `get all tasks`()= runTest {
        repository.addTask("task 7")
        repository.addTask("task 8")
        val tasks = repository.getAllTasks().first()
        assertEquals(2, tasks.size)
    }
}