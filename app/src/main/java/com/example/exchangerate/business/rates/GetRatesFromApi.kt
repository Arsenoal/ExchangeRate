package com.example.exchangerate.business.rates

import com.example.exchangerate.entity.Lang
import com.example.exchangerate.repo.rates.RatesListRepo

class GetRatesFromApi(
    private val ratesListRepo: RatesListRepo
): GetRatesUseCase {
    override suspend fun getRates(appLang: Lang) = ratesListRepo.getRates(appLang)
}