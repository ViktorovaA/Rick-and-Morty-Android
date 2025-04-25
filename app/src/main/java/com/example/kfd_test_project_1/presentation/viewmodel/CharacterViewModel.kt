package com.example.kfd_test_project_1.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.kfd_test_project_1.domain.entity.CharacterEntity
import com.example.kfd_test_project_1.domain.repository.IRickRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CharacterViewModel (
    private val repository: IRickRepository
): ViewModel() {
    private val _characters = MutableStateFlow(emptyList<CharacterEntity>())
    val characters: StateFlow<List<CharacterEntity>> = _characters.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> = _isError.asStateFlow()

    suspend fun loadCharacters(forceRefresh: Boolean = false) {
        _isLoading.value = true
        _isError.value = false
        try {
            val charactersList = repository.getAllCharacters(forceRefresh)
            _characters.value = charactersList
        } catch (e: Exception) {
            _isError.value = true
            Log.d("net", "loadCharacters: ${e.message}")
        } finally {
            _isLoading.value = false
        }
    }
}