package com.example.exchangerate.repo.rates.rates

import com.example.exchangerate.entity.rates.Organization
import com.example.exchangerate.repo.rates.model.toRateParams
import com.example.exchangerate.repo.rates.service.rates.RatesListService

class GetRatesListByRetrofitApi(
    private val ratesListService: RatesListService
): RatesListRepo {
    override suspend fun getRates(appLang: String): List<Organization> {
        val ratesResponse = ratesListService.rates(appLang)

        return ratesResponse.data.map {
            Organization(id = it.key, rateParams = it.value.toRateParams())
        }
    }
}