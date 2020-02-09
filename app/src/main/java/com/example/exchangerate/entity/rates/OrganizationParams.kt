package com.example.exchangerate.entity.rates

data class OrganizationParams(
    val organizationId: String,
    val organizationName: String,
    val organizationCityLocation: String,
    val organizationAddress: String,
    val organizationContacts: String,
    var workingHours: List<Pair<String, String>>,
    val organizationLocation: Pair<String, String>
)