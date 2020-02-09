package com.example.exchangerate.business.rates

import com.example.exchangerate.entity.Lang
import com.example.exchangerate.entity.rates.OrganizationParams

interface GetOrganizationParamsByIdUseCase {
    suspend fun getOrganization(
        organizationId: String,
        organizationName: String,
        appLang: Lang): List<OrganizationParams>
}