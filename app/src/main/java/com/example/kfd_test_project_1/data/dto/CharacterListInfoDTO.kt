package com.example.kfd_test_project_1.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CharacterListInfoDTO(
    val count: Int,
    val pages: Int,
    val next: String? = null,
    val prev: String? = null
)