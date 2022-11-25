package com.example.loggintp3.main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.loggintp3.databinding.FragmentCadastrarBandaBinding
import com.example.loggintp3.models.Banda
import com.example.loggintp3.repositorios.BandasRepository
import com.example.loggintp3.utils.toast

class CadastrarBandaFragment : Fragment() {

    private var _binding: FragmentCadastrarBandaBinding? = null

    private lateinit var repository: BandasRepository

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCadastrarBandaBinding.inflate(inflater, container, false)
        val view = binding.root
        setup()
        return view
    }

    private fun setup() {
        repository = BandasRepository.get()
        setupViews()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnCadastrar.setOnClickListener {
            noClickCadastrar()
        }
    }

    private fun noClickCadastrar() {
        val banda = pegarBandaDoInput()

        repository.cadastrarBanda(banda)
            .addOnSuccessListener { documentReference ->
                toast("Sucesso no cadatro com id : ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                toast("Falha cadastro")
            }
    }

    private fun pegarBandaDoInput(): Banda {
        return Banda(
            nomeBanda = binding.inputNomeBanda.text.toString(),
            generorBanda = binding.inputGenero.text.toString(),
            anoBanda = binding.inputAno.text.toString()
        )
    }

    private fun setupViews() {
        activity?.setTitle("Cadastrar bandas")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}