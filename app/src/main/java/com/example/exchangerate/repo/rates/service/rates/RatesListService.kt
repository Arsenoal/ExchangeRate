package com.example.exchangerate.repo.rates.service.rates

import com.example.exchangerate.repo.rates.model.KeyValueResponse
import com.example.exchangerate.repo.rates.model.Rate
import retrofit2.http.GET
import retrofit2.http.Query

interface RatesListService {
    @GET("rates.ashx")
    suspend fun rates(@Query("lang") appLang: String): KeyValueResponse<String, Rate>
}