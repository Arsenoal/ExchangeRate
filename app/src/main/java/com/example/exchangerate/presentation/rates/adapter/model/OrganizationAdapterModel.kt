package com.example.exchangerate.presentation.rates.adapter.model

import com.example.exchangerate.common.empty
import com.example.exchangerate.entity.rates.Currency

data class OrganizationRatesModel(
    val organizationAdapterModel: OrganizationAdapterModel,
    val currencies: List<Currency> = listOf()
)

data class OrganizationAdapterModel(
    val organizationId: String,
    val organizationName: String,
    val icon: String = String.empty(),
    val distance: String = String.empty(),
    val buy: String,
    val sell: String
)