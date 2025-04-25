package com.example.kfd_test_project_1.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CharacterDTO (
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String
)