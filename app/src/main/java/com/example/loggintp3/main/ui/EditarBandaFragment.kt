package com.example.loggintp3.main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.loggintp3.R
import com.example.loggintp3.databinding.FragmentCadastrarBandaBinding
import com.example.loggintp3.databinding.FragmentEditarBandaBinding

class EditarBandaFragment : Fragment() {

    private var _binding: FragmentEditarBandaBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditarBandaBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}