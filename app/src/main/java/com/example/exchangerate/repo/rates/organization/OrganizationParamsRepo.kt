package com.example.exchangerate.repo.rates.organization

import com.example.exchangerate.entity.Lang
import com.example.exchangerate.entity.rates.OrganizationParams

interface OrganizationParamsRepo {

    suspend fun get(
        organizationId: String,
        organizationName: String,
        appLang: Lang): List<OrganizationParams>
}