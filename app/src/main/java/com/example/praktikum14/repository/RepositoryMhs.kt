package com.example.praktikum14.repository

import com.example.praktikum14.model.Mahasiswa
import kotlinx.coroutines.flow.Flow

interface RepositoryMhs {
    suspend fun insertMhs(mahasiswa: Mahasiswa)
    fun getAllMhs(): Flow<List<Mahasiswa>>
    fun getMhs(nim: String): Flow<Mahasiswa>
    suspend fun updateMhs(mahasiswa: Mahasiswa)
    suspend fun deleteMhs(nim: String)
}