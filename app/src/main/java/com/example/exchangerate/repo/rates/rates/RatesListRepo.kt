package com.example.exchangerate.repo.rates.rates

import com.example.exchangerate.entity.rates.Organization

interface RatesListRepo {
    suspend fun getRates(appLang: String): List<Organization>
}