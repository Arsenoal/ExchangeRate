package com.example.exchangerate.business.organization

import com.example.exchangerate.entity.Lang
import com.example.exchangerate.entity.rates.OrganizationParams
import com.example.exchangerate.repo.organization.OrganizationParamsRepo

class GetOrganizationParamsByIdFromRetrofitApi(
    private val organizationParamsRepo: OrganizationParamsRepo
): GetOrganizationParamsByIdUseCase {

    override suspend fun getOrganization(
        organizationId: String,
        organizationName: String,
        appLang: Lang): List<OrganizationParams> {

        return organizationParamsRepo.get(
            organizationId = organizationId,
            organizationName = organizationName,
            appLang = appLang)
    }
}