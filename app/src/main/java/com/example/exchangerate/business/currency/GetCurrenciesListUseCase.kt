package com.example.exchangerate.business.currency

interface GetCurrenciesListUseCase {
    suspend fun getCurrencies(): List<String>
}