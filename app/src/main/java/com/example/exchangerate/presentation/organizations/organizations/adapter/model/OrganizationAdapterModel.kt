package com.example.exchangerate.presentation.organizations.organizations.adapter.model

import com.example.exchangerate.common.empty
import com.example.exchangerate.entity.rates.Currency

data class OrganizationRatesModel(
        val organizationAdapterModel: OrganizationAdapterModel,
        val mapModels: List<OrganizationMapModel> = emptyList(),
        val currencies: List<Currency> = emptyList()
)

data class OrganizationAdapterModel(
        val organizationId: String,
        val organizationName: String,
        val icon: String = String.empty(),
        val distance: String = String.empty(),
        val buy: String,
        val sell: String
)

data class OrganizationMapModel(
        val organizationId: String,
        val location: Location
) {
    data class Location(
            val lat: Float,
            val lng: Float
    )
}