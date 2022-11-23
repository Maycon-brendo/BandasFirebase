package com.example.loggintp3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loggintp3.databinding.ActivityLoginBinding
import com.example.loggintp3.repositorios.BandasRepository

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
            this
        )

    }
}