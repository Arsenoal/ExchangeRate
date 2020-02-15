package com.example.exchangerate.common

import com.example.exchangerate.entity.rates.Organization
import com.example.exchangerate.presentation.organizations.organizations.adapter.model.OrganizationAdapterModel

fun Organization.toOrganizationAdapterModelByCurrentCurrency(currencyName: String): OrganizationAdapterModel {
    val buy = rateParams
        .listCurrency
        .getCurrencyByName(currencyName)?.run {
            if (zero.buy.isNotBlank()) zero.buy
            else one.buy
        } ?: String.empty()

    val sell = rateParams
        .listCurrency
        .getCurrencyByName(currencyName)?.run {
            if (zero.sell.isNotBlank()) zero.sell
            else one.sell
        } ?: String.empty()

    return run {
        OrganizationAdapterModel(
                organizationId = id,
                organizationName = rateParams.title,
                icon = rateParams.logo,
                distance = String.empty(),
                buy = buy,
                sell = sell
        )
    }
}