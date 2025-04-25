package com.example.kfd_test_project_1.common.di

import androidx.room.Room
import com.example.kfd_test_project_1.common.database.AppDatabase
import com.example.kfd_test_project_1.domain.repository.IRickRepository
import com.example.kfd_test_project_1.data.repository.RickRepository
import com.example.kfd_test_project_1.data.service.RickApiService
import com.example.kfd_test_project_1.presentation.viewmodel.CharacterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val viewModelModule = module {
    viewModel { CharacterViewModel(get()) }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app_database"
        ).build()
    }
}

val repositoryModule = module {
    single<IRickRepository> { RickRepository(RickApiService, get()) }
}

val appModule = module {
    single { get<AppDatabase>().characterDAO() }
}