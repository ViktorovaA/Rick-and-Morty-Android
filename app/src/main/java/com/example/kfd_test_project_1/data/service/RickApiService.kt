package com.example.kfd_test_project_1.data.service

import com.example.kfd_test_project_1.common.api.NetworkModule
import com.example.kfd_test_project_1.data.dto.CharacterDTO
import com.example.kfd_test_project_1.data.dto.CharacterListDTO
import io.ktor.client.call.body
import io.ktor.client.request.get

object RickApiService {
    private const val BASE_URL = "https://rickandmortyapi.com/api"
    private var page = 1
    private var maxPage = 1

    suspend fun getAllCharacters(): CharacterListDTO {
        try {
            val response: CharacterListDTO = NetworkModule.publicClient
                .get("$BASE_URL/character/?page=$page")
                .body()
            maxPage = response.info.pages
            setPage(page + 1)
            return response
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

    private fun setPage(new: Int) {
        if (page in 1..maxPage) {
            page = new
        }
    }

    suspend fun getCharacterById(id: Int): CharacterDTO {
        return NetworkModule.publicClient.get("$BASE_URL/character/$id").body()
    }
}