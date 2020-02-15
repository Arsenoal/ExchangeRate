package com.example.exchangerate.repo.rates.service

import com.example.exchangerate.repo.commonmodels.KeyValueResponse
import com.example.exchangerate.repo.commonmodels.Rate
import retrofit2.http.GET
import retrofit2.http.Query

interface RatesListServiceRetrofit {
    @GET("rates.ashx")
    suspend fun rates(@Query("lang") appLang: String): KeyValueResponse<String, Rate>
}