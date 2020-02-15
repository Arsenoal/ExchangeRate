package com.example.exchangerate.repo.currency.service

import android.content.Context
import com.example.exchangerate.R

class CurrenciesFromLocalRes(
    private val context: Context
): CurrenciesService {
    override suspend fun getCurrencies() = with(context.resources) {
        getStringArray(R.array.currencies).asList()
    }
}