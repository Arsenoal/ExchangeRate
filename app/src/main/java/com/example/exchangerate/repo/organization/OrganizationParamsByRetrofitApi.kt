package com.example.exchangerate.repo.organization

import com.example.exchangerate.entity.Lang
import com.example.exchangerate.entity.rates.OrganizationParams
import com.example.exchangerate.repo.commonmodels.toEntity
import com.example.exchangerate.repo.organization.service.OrganizationServiceRetrofit

class OrganizationParamsByRetrofitApi(
    private val organizationServiceRetrofit: OrganizationServiceRetrofit
): OrganizationParamsRepo {

    override suspend fun get(organizationId: String, organizationName: String, appLang: Lang): List<OrganizationParams> {

        return organizationServiceRetrofit.getOrganizationParams(organizationId).data.map { pair ->
            pair.value.toEntity(
                organizationId = pair.key,
                organizationName = organizationName,
                lang = appLang)
        }
    }
}