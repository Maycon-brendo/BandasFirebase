package com.example.loggintp3.repositorios

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

    fun cadastrarUsuarioComSenha(email: String, senha: String, context: AppCompatActivity) {
        auth.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener(context) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    //val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        context, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    fun login(email: String, password: String, context: AppCompatActivity) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(context) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        context, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
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