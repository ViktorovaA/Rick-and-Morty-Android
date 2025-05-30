package com.example.kfd_test_project_1.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterModel(
//    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val image: String,
    val status: String,
    val species: String,
)