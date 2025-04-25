package com.example.kfd_test_project_1.data.repository

import com.example.kfd_test_project_1.data.mapper.CharacterMapper
import com.example.kfd_test_project_1.data.service.CharacterDAO
import com.example.kfd_test_project_1.data.service.RickApiService
import com.example.kfd_test_project_1.domain.entity.CharacterEntity
import com.example.kfd_test_project_1.domain.repository.IRickRepository

class RickRepository(
    private val apiService: RickApiService,
    private val dao: CharacterDAO,
): IRickRepository {
    override suspend fun getAllCharacters(forceRefresh: Boolean): List<CharacterEntity> {
        try {
            val localData = dao.getAllCharacters()
            if (localData.isEmpty() || forceRefresh){
                val remoteData = apiService.getAllCharacters()
                // TODO: save remote data to local database
                dao.insertAll(remoteData.results.map { CharacterMapper.mapDTOToModel(it) })
                return remoteData.results.map { CharacterMapper.mapDTOEntity(it) }
            }
            return localData.map { CharacterMapper.mapModelToEntity(it)}
        } catch (e: Exception) {
            throw Exception(e.message + "|" + e.localizedMessage, e)
        }
    }
}