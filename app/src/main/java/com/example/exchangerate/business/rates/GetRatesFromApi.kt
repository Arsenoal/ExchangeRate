package com.example.exchangerate.business.rates

import com.example.exchangerate.repo.rates.rates.RatesListRepo

class GetRatesFromApi(
    private val ratesListRepo: RatesListRepo
): GetRatesUseCase {
    override suspend fun getRates(appLang: String) = ratesListRepo.getRates(appLang)
}