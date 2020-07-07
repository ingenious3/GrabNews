# GrabNews

What is GrabNews?

A simple Android application that serves as a news article client.
News data is fetched from https://newsapi.org/ API.
Data is refreshed automatically, and you can manually refresh it too.

Main Design Principles
1. Language: Kotlin
2. Google Architecture Components: MVVM architecture + LiveData + Room database
3. Kotlin Coroutines to handle asynchronous operations
4. Retrofit 2 for networking
5. Dagger 2 for dependency injection
6. Room database as the single source of truth for UI data, so that the app can work offline
