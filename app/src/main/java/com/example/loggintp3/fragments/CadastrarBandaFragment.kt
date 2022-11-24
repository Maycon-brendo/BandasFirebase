package com.example.loggintp3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.loggintp3.R
import com.example.loggintp3.databinding.FragmentBandasBinding
import com.example.loggintp3.databinding.FragmentCadastrarBandaBinding

class CadastrarBandaFragment : Fragment() {

    private var _binding: FragmentCadastrarBandaBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCadastrarBandaBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}