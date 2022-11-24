package com.example.loggintp3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loggintp3.databinding.ActivityLoginBinding
import com.example.loggintp3.databinding.ActivityMainBinding
import com.example.loggintp3.repositorios.BandasRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var repository: BandasRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setup()
    }

    private fun setup() {
        setupclickListener()
        repository = BandasRepository.get()
    }

    private fun setupclickListener() {
        binding.button.setOnClickListener{
            repository.logout()
            iniciarLoginActivity()
        }
    }

    private fun iniciarLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun setupView(){
        binding.aaa.text = "seja bem vindo ${repository.pegarUsuarioAtual()?.email}"
    }
}