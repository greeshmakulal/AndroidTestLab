package com.example.androidtestlab.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.androidtestlab.model.TaskItem
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TaskScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    @Test
    fun testEmotyList() {
        composeTestRule.setContent {
            TaskScreen(
                uiState =
                    TaskUIState(tasks = emptyList()),
                onAddTask = {},
                onToggleTask = {},
                onDeleteTask = {})
        }
        composeTestRule.onNodeWithText("No Tasks Yet").assertIsDisplayed()

    }
    @Test
    fun addTask() {
        composeTestRule.setContent {
            TaskScreen(
                uiState =
                    TaskUIState(tasks = listOf(TaskItem(id = 1, title = "Fill bottle"))),
                onAddTask = {},
                onToggleTask = {},
                onDeleteTask = {})
        }
        composeTestRule.onNodeWithText("Fill bottle").assertIsDisplayed()

    }
    @Test
    fun addTaskAction() {
        var addedTask =""
        composeTestRule.setContent {
            TaskScreen(
                uiState =
                    TaskUIState(),
                onAddTask = { addedTask = it},
                onToggleTask = {},
                onDeleteTask = {})
        }
        composeTestRule.onNodeWithText("Add a new task...").performTextInput("Learn Kubernetes")
        composeTestRule.onNodeWithContentDescription("Add Task").performClick()

        assertEquals(
            "Learn Kubernetes",
            addedTask
        )

    }
    //because in code there is a check if title is not blank-  if blank then it will enter add block
    @Test
    fun addTaskBlankInputAction() {
        var addedTask =""
        var isAddTaskTriggered = false
        composeTestRule.setContent {
            TaskScreen(
                uiState =
                    TaskUIState(),
                onAddTask = { isAddTaskTriggered = true},
                onToggleTask = {},
                onDeleteTask = {})
        }
        composeTestRule.onNodeWithText("Add a new task...").performTextInput(addedTask)
        composeTestRule.onNodeWithContentDescription("Add Task").performClick()

        assertEquals(
            false,
            isAddTaskTriggered
        )

    }
}