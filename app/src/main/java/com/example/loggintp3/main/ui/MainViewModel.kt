package com.example.loggintp3.main.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loggintp3.models.Banda
import com.example.loggintp3.models.BandaComId
import com.example.loggintp3.repositorios.BandasRepository
import com.google.firebase.firestore.ktx.toObject

class MainViewModel: ViewModel() {

    val TAG = "ViewModel"


    val repository = BandasRepository.get()

    fun pegarBandas(): List<Banda> {
        val lista = mutableListOf<Banda>()
        repository.pegarBandas()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val banda = document.toObject<Banda>()
                    lista.add(banda)
                    Log.i(TAG, "document: ${document}")
                    Log.i(TAG, "banda: ${banda}")
                }
                setTurmas(lista)
            }
            .addOnFailureListener { exception ->

            }
        return lista
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Bandas //////////////////////////////////////////////////////////////////////////////////////
    private val _bandas = MutableLiveData<List<Banda>>()
    val bandas: LiveData<List<Banda>> = _bandas
    fun setTurmas(value: List<Banda>) {
        _bandas.postValue(value)
    }

    private val _bandasComId = MutableLiveData<List<BandaComId>>()
    val bandasComId: LiveData<List<BandaComId>> = _bandasComId
    fun setBandasComId(value: List<BandaComId>) {
        _bandasComId.postValue(value)
    }

    fun bandaToBandaComId(banda: Banda, id: String): BandaComId {
        return BandaComId(
            nomeBanda = banda.nomeBanda,
            generoBanda = banda.generorBanda,
            formacaoBanda = banda.anoBanda,
            id = id
        )
    }

    fun deleteBanda(id: String) {
        repository.deleteBanda(id)
    }

    private val _selectedBandaComId = MutableLiveData<BandaComId>()
    val selectedBandaComId: LiveData<BandaComId> = _selectedBandaComId
    fun setSelectedBandaComId(value: BandaComId) {
        _selectedBandaComId.postValue(value)
    }

    fun atualizaBanda(banda: Banda) {
        repository.atualizaBanda(selectedBandaComId.value?.id, banda)
    }

}