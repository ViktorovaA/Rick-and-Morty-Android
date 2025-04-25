package com.example.kfd_test_project_1.data.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kfd_test_project_1.data.model.CharacterModel

@Dao
interface CharacterDAO {
    @Query("SELECT * FROM characters")
    suspend fun getAllCharacters(): List<CharacterModel>

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getCharactersById(id: Int): CharacterModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(publications: List<CharacterModel>)
}