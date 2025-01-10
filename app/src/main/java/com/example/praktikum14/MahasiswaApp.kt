package com.example.praktikum14

import android.app.Application
import com.example.praktikum14.di.MahasiswaContainer

class MahasiswaApp: Application() {
    lateinit var containerApp: MahasiswaContainer
    override fun onCreate(){
        super.onCreate()
        containerApp = MahasiswaContainer(this)
    }
}