package com.example.exchangerate.repo.rates.service.currency

interface CurrenciesService {
    suspend fun getCurrencies(): List<String>
}