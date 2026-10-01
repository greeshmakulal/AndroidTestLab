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

## Purpose

This project was created as a practical Android testing exercise to go beyond basic unit tests and understand how different layers of a real Android application can be tested independently.

The focus is not only on writing tests, but also on structuring Android code in a way that makes applications easier to test, maintain, and scale.
