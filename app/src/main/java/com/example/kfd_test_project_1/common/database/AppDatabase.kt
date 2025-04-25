package com.example.kfd_test_project_1.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kfd_test_project_1.data.model.CharacterModel
import com.example.kfd_test_project_1.data.service.CharacterDAO

@Database(
    entities = [CharacterModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDAO(): CharacterDAO
}