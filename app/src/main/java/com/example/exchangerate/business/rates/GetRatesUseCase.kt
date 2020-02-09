package com.example.exchangerate.business.rates

import com.example.exchangerate.entity.rates.Organization

interface GetRatesUseCase {
    suspend fun getRates(appLang: String): List<Organization>
}