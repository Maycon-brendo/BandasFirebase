package com.example.loggintp3.repositorios

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.lang.IllegalStateException


const val TAG = "BandasFirebase"

class BandasRepository private constructor() {


// ...
// Initialize Firebase Auth


    companion object {
        private lateinit var auth: FirebaseAuth
        private var INSTACE: BandasRepository? = null
        fun initializer() {
            if (INSTACE == null) {
                INSTACE = BandasRepository()
            }
            auth = Firebase.auth
        }

        fun get(): BandasRepository {
            return INSTACE
                ?: throw IllegalStateException("BandasRepository Deve ser inicializado")
        }
    }

    fun pegarUsuarioAtual() = auth.currentUser

    fun cadastrarUsuarioComSenha(email: String, senha: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, senha)

    }

    fun login(
        email: String,
        password: String
    ): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email, password)
    }

    fun estaLogado(): Boolean {
        if (pegarUsuarioAtual() != null) {
            return true
        }
        return false
    }

    fun logout() {
        Firebase.auth.signOut()
    }
}