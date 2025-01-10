package com.example.praktikum14.repository

import com.example.praktikum14.model.Mahasiswa
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class NetworkRepositoryMhs(
    private val firestore: FirebaseFirestore
): RepositoryMhs {
    override suspend fun insertMhs(mahasiswa: Mahasiswa) {
        TODO("Not yet implemented")
    }

    override fun getAllMhs(): Flow<List<Mahasiswa>> = callbackFlow {
        val mhsCollection = firestore.collection("Mahasiswa")
            .orderBy("nim", Query.Direction.ASCENDING)
            .addSnapshotListener{
                value, error ->
                if(value != null){
                    val mhsList = value.documents.mapNotNull {
                        //convert dari document firestore ke data class
                        it.toObject(Mahasiswa::class.java)
                    }
                    //Fungsi untuk mengirim collection ke data class
                    trySend(mhsList)
                }
            }
        awaitClose{
            //Menutup collection dari firestore
            mhsCollection.remove()
        }
    }

    override suspend fun getMhs(): List<Mahasiswa> {
        TODO("Not yet implemented")
    }

    override suspend fun updateMhs(nim: String, mahasiswa: Mahasiswa) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMhs(nim: String) {
        TODO("Not yet implemented")
    }

}