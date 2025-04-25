package com.example.kfd_test_project_1.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CharacterListDTO(
    val info: CharacterListInfoDTO,
    val results: List<CharacterDTO>
)
