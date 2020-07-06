package com.example.grabnews.util

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.example.grabnews.TestApp

class NewsAppTestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, TestApp::class.java.name, context)
    }
}