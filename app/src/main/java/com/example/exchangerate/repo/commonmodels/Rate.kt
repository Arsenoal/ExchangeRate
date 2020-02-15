package com.example.exchangerate.repo.commonmodels

import com.example.exchangerate.common.empty
import com.example.exchangerate.entity.rates.RateParams
import com.google.gson.annotations.SerializedName

data class Rate(
    @SerializedName("title") val title: String,
    @SerializedName("date") val date: String,
    @SerializedName("logo") val logo: String,
    @SerializedName("list") val listCurrency: Map<String, Currency>
)

data class Currency(
    @SerializedName("0") val zero: BuySell,
    @SerializedName("1") val one: BuySell
)

data class BuySell(
    @SerializedName("buy") val buy: String?,
    @SerializedName("sell") val sell: String?
)

fun Rate.toRateParams(): RateParams {
    val list = mutableListOf<com.example.exchangerate.entity.rates.Currency>()

    listCurrency.forEach { pair ->
        list.add(com.example.exchangerate.entity.rates.Currency(
            title = pair.key,
            zero = com.example.exchangerate.entity.rates.BuySell(
                buy = pair.value.zero.buy ?: String.empty(),
                sell = pair.value.zero.sell ?: String.empty()),
            one = com.example.exchangerate.entity.rates.BuySell(
                buy = pair.value.one.buy ?: String.empty(),
                sell = pair.value.one.sell ?: String.empty())
        ))
    }

    return RateParams(
        title = title,
        date = date,
        logo = logo,
        listCurrency = list
    )
}