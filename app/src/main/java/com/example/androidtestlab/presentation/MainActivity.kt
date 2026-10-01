package com.example.androidtestlab.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidtestlab.data.local.TaskDatabase
import com.example.androidtestlab.data.repository.TaskRepositoryImpl
import com.example.androidtestlab.ui.theme.AndroidTestLabTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = TaskDatabase.getDataBase(this)

        val repository = TaskRepositoryImpl(
            taskDao = database.getTaskDao()
        )
        val viewModel = TaskViewModel(repository)
//        enableEdgeToEdge()
        setContent {
            AndroidTestLabTheme {
                val uiState by viewModel.uiState.collectAsState()

                TaskScreen(
                    uiState = uiState,
                    onAddTask = viewModel::addTask,
                    onToggleTask = viewModel::toggleTask,
                    onDeleteTask = viewModel::deleteTask
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidTestLabTheme {
        Greeting("Android")
    }
}