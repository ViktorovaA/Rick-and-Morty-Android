package com.example.kfd_test_project_1.data.repository

import com.example.kfd_test_project_1.common.errors.NetworkUnavailableException
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
        val localData = dao.getAllCharacters()
        try {
            if (localData.isEmpty() || forceRefresh){
                val remoteData = apiService.getAllCharacters()
                dao.insertAll(remoteData.results.map { CharacterMapper.mapDTOToModel(it) })
//                return remoteData.results.map { CharacterMapper.mapDTOEntity(it) }
                return dao.getAllCharacters().map { CharacterMapper.mapModelToEntity(it) }
            }
            return localData.map { CharacterMapper.mapModelToEntity(it)}
        } catch (e: Exception) {
            if (localData.isNotEmpty()) {
                throw NetworkUnavailableException("Нет подключения к интернету. " +
                        "Загружены локальные данные.")
            } else {
                throw Exception(e.message + "|" + e.localizedMessage, e)
            }
        }
    }
}