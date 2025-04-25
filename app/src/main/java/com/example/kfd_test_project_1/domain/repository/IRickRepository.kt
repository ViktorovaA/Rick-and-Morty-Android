package com.example.kfd_test_project_1.domain.repository

import com.example.kfd_test_project_1.domain.entity.CharacterEntity

interface IRickRepository {
    suspend fun getAllCharacters(forceRefresh: Boolean = false): List<CharacterEntity>
}