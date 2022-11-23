package com.example.loggintp3.application

import android.app.Application
import com.example.loggintp3.repositorios.BandasRepository


//adicionar ao manifesto dentro da tag application:
//android:name=".application.BandasApplication"

class BandasApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        BandasRepository.inicializar()
    }

    fun login(email: String, password: String){

    }

}