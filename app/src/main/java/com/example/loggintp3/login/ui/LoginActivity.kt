package com.example.loggintp3.login.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.loggintp3.main.ui.MainActivity
import com.example.loggintp3.databinding.ActivityLoginBinding
import com.example.loggintp3.repositorios.BandasRepository
import com.example.loggintp3.repositorios.TAG

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var repository: BandasRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setup()
    }

    override fun onStart() {
        super.onStart()

        if (repository.estaLogado()) {
            iniciarMainActivity()
        }
    }

    private fun iniciarMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun setup() {
        setupClickListener()

        repository = BandasRepository.get()
    }

    private fun setupClickListener() {
        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, RegistrarActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            noClickLogar()
        }
    }

    private fun noClickLogar() {
        repository.login(
            binding.inputEmail.text.toString(),
            binding.inputPassword.text.toString(),
        )
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "Login com sucesso")
                    iniciarMainActivity()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        this, "Autenticação falhou.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

    }
}