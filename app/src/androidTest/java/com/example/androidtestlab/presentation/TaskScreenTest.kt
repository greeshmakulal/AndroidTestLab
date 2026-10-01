package com.example.androidtestlab.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.androidtestlab.model.TaskItem
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TaskScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    @Test
    fun testTasUI() {
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
}