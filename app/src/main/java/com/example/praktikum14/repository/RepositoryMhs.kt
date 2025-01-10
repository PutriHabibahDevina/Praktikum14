package com.example.praktikum14.repository

import com.example.praktikum14.model.Mahasiswa
import kotlinx.coroutines.flow.Flow

interface RepositoryMhs {
    suspend fun insertMhs(mahasiswa: Mahasiswa)
    fun getAllMhs(): Flow<List<Mahasiswa>>
    suspend fun getMhs(): List<Mahasiswa>
    suspend fun updateMhs(nim:String, mahasiswa: Mahasiswa)
    suspend fun deleteMhs(nim: String)
}