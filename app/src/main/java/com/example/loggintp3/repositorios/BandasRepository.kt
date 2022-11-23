package com.example.loggintp3.repositorios

import java.lang.IllegalStateException

class BandasRepository {

    companion object {
        private var INSTACE: BandasRepository? = null
        fun inicializar() {
            if (INSTACE == null) {
                INSTACE = BandasRepository()
            }
        }

        fun get(): BandasRepository {
            return INSTACE
                ?: throw IllegalStateException("BandasRepository Deve ser inicializado")
        }
    }
}