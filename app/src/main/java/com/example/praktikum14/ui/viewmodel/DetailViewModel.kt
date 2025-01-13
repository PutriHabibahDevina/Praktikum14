package com.example.praktikum14.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praktikum14.model.Mahasiswa
import com.example.praktikum14.repository.RepositoryMhs
import kotlinx.coroutines.launch

class DetailViewModel (
    private val repositoryMhs: RepositoryMhs
): ViewModel() {
    var uiState by mutableStateOf(DetailUiState())
        private set

    fun detailMhs(nim: String) {
        viewModelScope.launch {
            try {
                val mahasiswa = repositoryMhs.getMhs(nim)
                uiState = mahasiswa.toUiStateDetail()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class DetailUiState(
    val nim: String = "",
    val nama: String = "",
    val alamat: String = "",
    val jenisKelamin: String = "",
    val kelas: String = "",
    val angkatan: String = "",
    val judulSkripsi: String = "",
    val dosbim1: String = "",
    val dosbim2: String = "",
)

fun Mahasiswa.toUiStateDetail(): DetailUiState = DetailUiState(
    nim = nim,
    nama = nama,
    alamat = alamat,
    jenisKelamin = jenisKelamin,
    kelas = kelas,
    angkatan = angkatan,
    judulSkripsi = judulSkripsi,
    dosbim1 = dospbim1,
    dosbim2 = dosbim2
)