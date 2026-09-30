package com.example.androidtestlab.ui

import com.example.androidtestlab.FakeRepository
import com.example.androidtestlab.model.TaskItem
import com.example.androidtestlab.presentation.TaskViewModel
import com.example.androidtestlab.util.MainDispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TaskViewModelTest {

    @get:Rule
    val mainDispatchers = MainDispatchers()

    @Test
    fun `get the items from repository and update in ui state this happens in viewmodel`() =
        runTest {
            val repository = FakeRepository()
            repository.setTasks(
                listOf(
                    TaskItem(id = 1, title = "Fill bottle"),
                    TaskItem(id = 2, title = "Complete testing")
                )
            )
            val viewModel = TaskViewModel(repository)
            advanceUntilIdle() // which tells coroutines to execute all before moving to the result
            Assert.assertEquals(2, viewModel.uiState.value.tasks.size)
            Assert.assertEquals("Fill bottle", viewModel.uiState.value.tasks[0].title)
        }

    @Test
    fun `add task from viewmodel`() = runTest {
        val repository = FakeRepository()
        repository.setTasks(
            listOf(
                TaskItem(id = 1, title = "Fill bottle"),
                TaskItem(id = 2, title = "Complete testing")
            )
        )
        val viewModel = TaskViewModel(repository)

        viewModel.addTask("Buy Book")
        advanceUntilIdle()
        Assert.assertEquals(3, viewModel.uiState.value.tasks.size)
    }
}

