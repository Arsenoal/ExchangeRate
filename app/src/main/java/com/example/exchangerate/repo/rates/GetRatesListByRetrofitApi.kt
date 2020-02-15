package com.example.exchangerate.repo.rates

import com.example.exchangerate.entity.Lang
import com.example.exchangerate.entity.rates.Organization
import com.example.exchangerate.repo.commonmodels.toRateParams
import com.example.exchangerate.repo.rates.service.RatesListServiceRetrofit

class GetRatesListByRetrofitApi(
    private val ratesListServiceRetrofit: RatesListServiceRetrofit
): RatesListRepo {
    override suspend fun getRates(appLang: Lang): List<Organization> {
        val ratesResponse = ratesListServiceRetrofit.rates(appLang.lang)

        return ratesResponse.data.map {
            Organization(id = it.key, rateParams = it.value.toRateParams())
        }
    }
}