# Android Testing Lab

Android Testing Lab is a small Task Management application built to practice different testing techniques in Android.

The main goal of this project is to understand how to test different layers of an Android application, including the ViewModel, repository, Room database, coroutines, Flow, and Jetpack Compose UI.

## Features

- Add a task
- View all tasks
- Mark a task as completed
- Delete a task
- Track completed tasks
- Persist tasks using Room Database
- Modern UI built with Jetpack Compose

## Tech Stack

- Kotlin
- Jetpack Compose
- MVVM
- Repository Pattern
- Room Database
- Kotlin Coroutines
- Flow / StateFlow
- JUnit
- Kotlin Coroutines Test
- AndroidJUnit4
- Jetpack Compose UI Testing
- Android Instrumentation Testing

## Project Architecture

The application follows a simple MVVM structure:

```text
Compose UI
    ↓
ViewModel
    ↓
Repository
    ↓
Room DAO
    ↓
Room Database
```

The repository abstraction also allows the ViewModel to be tested independently using a fake repository.

## Testing Covered

### 1. Repository Testing

A `FakeRepository` is used to provide controlled test data without depending on the real Room database.

Test scenarios include:

- Adding a task
- Retrieving tasks
- Updating tasks
- Deleting tasks

### 2. ViewModel Testing

The `TaskViewModel` is tested using:

- JUnit
- `kotlinx-coroutines-test`
- `StandardTestDispatcher`
- `MainDispatcherRule`

Test cases verify that:

- Tasks from the repository are reflected in `uiState`
- Adding a task updates the state correctly
- Coroutine operations complete before assertions are performed

Example:

```kotlin
@Test
fun `add task from viewmodel`() = runTest {

    val repository = FakeRepository()
    val viewModel = TaskViewModel(repository)

    viewModel.addTask("Buy Book")

    advanceUntilIdle()

    assertEquals(1, viewModel.uiState.value.tasks.size)
}
```

### 3. Room Database Testing

Room DAO operations are tested using an in-memory database.

```kotlin
Room.inMemoryDatabaseBuilder(
    ApplicationProvider.getApplicationContext(),
    TaskDatabase::class.java
).build()
```

This allows Room to be tested using a temporary database without modifying the application's actual database.

Test scenarios include:

- Insert task
- Retrieve tasks
- Update task
- Delete task

These tests run as Android instrumentation tests.

### 4. Jetpack Compose UI Testing

Compose UI tests verify how the screen behaves for different UI states.

Example:

```kotlin
@Test
fun emptyTaskList_showsEmptyState() {

    composeTestRule.setContent {
        TaskScreen(
            uiState = TaskUIState(tasks = emptyList()),
            onAddTask = {},
            onToggleTask = {},
            onDeleteTask = {}
        )
    }

    composeTestRule
        .onNodeWithText("No tasks yet")
        .assertIsDisplayed()
}
```

UI test scenarios include:

- Empty task state is displayed
- Existing tasks are displayed
- User can enter a task
- Add button triggers the correct callback
- Blank tasks are not added
- Task completion state can be verified
- Delete action can be tested

## Local Unit Tests vs Instrumentation Tests

### Local Unit Tests

Located under:

```text
src/test/
```

Used for:

- ViewModel testing
- Fake Repository testing
- Coroutine testing
- Flow testing

These tests run directly on the JVM and are fast.

### Instrumentation Tests

Located under:

```text
src/androidTest/
```

Used for:

- Room database testing
- Jetpack Compose UI testing

These tests run on an Android emulator or physical device.

## Key Learnings

Through this project I practiced:

- Isolating application layers during testing
- Using fake repositories for ViewModel tests
- Testing suspend functions using `runTest`
- Controlling coroutine execution using `StandardTestDispatcher`
- Using `advanceUntilIdle()` to execute pending coroutine work
- Replacing `Dispatchers.Main` during local unit tests
- Testing `Flow` and `StateFlow`
- Testing Room using an in-memory database
- Writing Android instrumentation tests
- Writing Jetpack Compose UI tests
- Structuring Android code to make it easier to test

## Project Structure

```text
com.example.androidtestlab

├── data
│   ├── local
│   │   ├── TaskEntity
│   │   ├── TaskDao
│   │   └── TaskDatabase
│   │
│   └── repository
│       ├── TaskRepository
│       └── TaskRepositoryImpl
│
├── model
│   ├── TaskItem
│   └── TaskMapper
│
├── presentation
│   ├── TaskViewModel
│   ├── TaskUIState
│   ├── TaskScreen
│   └── MainActivity
│
├── test
│   ├── FakeRepository
│   ├── FakeRepositoryTest
│   ├── TaskViewModelTest
│   └── MainDispatcherRule
│
└── androidTest
    ├── TaskDaoTest
    └── TaskScreenTest
```

## Purpose

This project was created as a practical Android testing exercise to go beyond basic unit tests and understand how different layers of a real Android application can be tested independently.

The focus is not only on writing tests, but also on structuring Android code in a way that makes applications easier to test, maintain, and scale.
