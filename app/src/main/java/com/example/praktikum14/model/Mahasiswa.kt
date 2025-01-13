package com.example.praktikum14.model

data class Mahasiswa(
    val nim: String,
    val nama: String,
    val alamat: String,
    val jenisKelamin: String,
    val kelas: String,
    val angkatan: String,
    val judulSkripsi: String,
    val dospbim1: String,
    val dosbim2: String,
) {
    constructor() : this("", "", "", "", "", "", ", ", "", "")
}
