package com.example.loggintp3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loggintp3.databinding.ActivityLoginBinding
import com.example.loggintp3.databinding.ActivityRegistrarBinding
import com.example.loggintp3.repositorios.BandasRepository

class RegistrarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrarBinding

    private lateinit var repository: BandasRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setup()
    }

    private fun setup() {
        setupClickListeners()
        repository = BandasRepository.get()
    }

    private fun setupClickListeners() {
        binding.btnRegistrar.setOnClickListener {
            noClickCadastrar()
        }
    }

    private fun noClickCadastrar() {
        repository.cadastrarUsuarioComSenha(
                binding.inputEmailRegistrar.text.toString(),
                binding.inputPasswordRegistrar.text.toString(),
                this
        )
    }

    fun validarSenha(senha: String): Boolean {
        return senha.length >= 6

    }

    fun iniciarMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
