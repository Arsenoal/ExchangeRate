package com.example.exchangerate.repo.currency.service

interface CurrenciesService {
    suspend fun getCurrencies(): List<String>
}