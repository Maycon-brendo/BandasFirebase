package com.example.loggintp3.main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loggintp3.R
import com.example.loggintp3.databinding.FragmentBandasBinding
import com.example.loggintp3.main.ui.adapters.BandaComIdAdapter
import com.example.loggintp3.main.ui.adapters.BandaComIdListener
import com.example.loggintp3.models.BandaComId
import com.example.loggintp3.utils.nav
import com.example.loggintp3.utils.toast

class BandasFragment : Fragment() {

    val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentBandasBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBandasBinding.inflate(inflater, container, false)
        val view = binding.root
        setup()
        return view
    }

    private fun setup() {
        setupViews()
        setupClickListeners()
        setupRecyclerView()
        setupObservers()
    }

    private fun setupClickListeners() {
        binding.btnCadastrar.setOnClickListener {

            nav(R.id.action_bandasFragment_to_cadastrarBandaFragment)
        }
    }

    private fun setupViews() {
        activity?.setTitle("Bandas")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    val adapter = BandaComIdAdapter(
        object : BandaComIdListener {
            override fun onEditClick(banda: BandaComId) {
                viewModel.setSelectedBandaComId(banda)
                nav(R.id.action_bandasFragment_to_editarBandaFragment)
            }

            override fun onDeleteClick(banda: BandaComId) {
                viewModel.deleteBanda(banda.id)
            }
        }
    )

    private fun setupRecyclerView() {
        // adapter precisa ser uma vari??vel global para ser acessada por todos os m??todos
        binding.rvBandas.adapter = adapter
        binding.rvBandas.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    private fun setupObservers() {
        viewModel.bandasComId.observe(viewLifecycleOwner) {
            atualizaRecyclerView(it)
        }
    }

    fun atualizaRecyclerView(lista: List<BandaComId>) {
        adapter.submitList(lista)
        binding.rvBandas.adapter = adapter
    }
}