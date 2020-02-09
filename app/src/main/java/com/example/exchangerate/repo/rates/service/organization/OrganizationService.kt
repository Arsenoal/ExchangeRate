package com.example.exchangerate.repo.rates.service.organization

import com.example.exchangerate.repo.rates.model.KeyValueResponse
import com.example.exchangerate.repo.rates.model.OrganizationParams
import retrofit2.http.GET
import retrofit2.http.Query

interface OrganizationService {
    @GET("branches.ashx")
    suspend fun getOrganizationParams(@Query("id") organizationId: String): KeyValueResponse<String, OrganizationParams>
}