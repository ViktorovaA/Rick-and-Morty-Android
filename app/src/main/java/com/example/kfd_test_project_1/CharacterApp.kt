package com.example.kfd_test_project_1

import android.app.Application
import com.example.kfd_test_project_1.common.di.viewModelModule
import com.example.kfd_test_project_1.common.di.appModule
import com.example.kfd_test_project_1.common.di.databaseModule
import com.example.kfd_test_project_1.common.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class CharacterApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CharacterApp)
            modules(listOf(viewModelModule, databaseModule, repositoryModule, appModule))
        }
    }
}