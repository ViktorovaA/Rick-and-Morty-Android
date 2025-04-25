package com.example.kfd_test_project_1.domain.entity

import android.net.Uri

data class CharacterEntity (
    val name: String,
    val image: Uri,
    val status: String,
    val species: String,
)