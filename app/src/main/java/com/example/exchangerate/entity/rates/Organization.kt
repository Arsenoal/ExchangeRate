package com.example.exchangerate.entity.rates

import com.example.exchangerate.common.empty

data class Organization(
    val id: String,
    val rateParams: RateParams
)

data class RateParams(
    val title: String,
    val date: String,
    val logo: String,
    val listCurrency: List<Currency>
)

data class Currency(
    val title: String,
    val zero: BuySell,
    val one: BuySell
)

data class BuySell(
    val buy: String = String.empty(),
    val sell: String = String.empty()
)