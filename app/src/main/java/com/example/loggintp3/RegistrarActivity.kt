package com.example.loggintp3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loggintp3.databinding.ActivityLoginBinding
import com.example.loggintp3.databinding.ActivityRegistrarBinding

class RegistrarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setup()
    }

    private fun setup() {
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnRegistrar.setOnClickListener {
            cadastrarClick()
        }
    }

    private fun cadastrarClick() {

    }

    fun validarSenha(senha: String): Boolean {
        return senha.length >= 6

    }

    fun iniciarMain(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    fun estaLogado(){
        //se ja estiver logado vai direto pra main

    }
}
