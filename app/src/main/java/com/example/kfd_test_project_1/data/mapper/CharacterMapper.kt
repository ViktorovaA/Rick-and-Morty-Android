package com.example.kfd_test_project_1.data.mapper

import android.net.Uri
import com.example.kfd_test_project_1.data.dto.CharacterDTO
import com.example.kfd_test_project_1.domain.entity.CharacterEntity
import com.example.kfd_test_project_1.data.model.CharacterModel

abstract class CharacterMapper {
    companion object{
        fun mapDTOEntity(dto: CharacterDTO): CharacterEntity {
            return CharacterEntity(
                name = dto.name,
                image = Uri.parse(dto.image),
                status = dto.status,
                species = dto.species
            )
        }
        fun mapModelToEntity(model: CharacterModel): CharacterEntity{
            return CharacterEntity(
                name = model.name,
                image = Uri.parse(model.image),
                status = model.status,
                species = model.species
            )
        }
        fun mapDTOToModel(dto: CharacterDTO): CharacterModel{
            return CharacterModel(
                name = dto.name,
                image = dto.image,
                status = dto.status,
                species = dto.species
            )
        }
    }
}