package com.example.exchangerate.business.rates

import com.example.exchangerate.entity.Lang
import com.example.exchangerate.entity.rates.Organization

interface GetRatesUseCase {
    suspend fun getRates(appLang: Lang): List<Organization>
}