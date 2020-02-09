package com.example.exchangerate.repo.rates.organization

import com.example.exchangerate.entity.Lang
import com.example.exchangerate.entity.rates.OrganizationParams
import com.example.exchangerate.repo.rates.model.toEntity
import com.example.exchangerate.repo.rates.service.organization.OrganizationService

class OrganizationParamsByRetrofitApi(
    private val organizationService: OrganizationService
): OrganizationParamsRepo {

    override suspend fun get(organizationId: String, organizationName: String, appLang: Lang): List<OrganizationParams> {

        return organizationService.getOrganizationParams(organizationId).data.map { pair ->
            pair.value.toEntity(
                organizationId = pair.key,
                organizationName = organizationName,
                lang = appLang)
        }
    }
}