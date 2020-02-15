package com.example.exchangerate.repo.rates

import com.example.exchangerate.entity.Lang
import com.example.exchangerate.entity.rates.Organization

interface RatesListRepo {
    suspend fun getRates(appLang: Lang): List<Organization>
}