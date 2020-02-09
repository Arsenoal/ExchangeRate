package com.example.exchangerate.repo.rates.service.currency

import android.content.Context
import com.example.exchangerate.R

class CurrenciesFromLocalRes(
    private val context: Context
): CurrenciesService {
    override suspend fun getCurrencies(): List<String> {
        return context.resources.getStringArray(R.array.currencies).asList()
    }
}