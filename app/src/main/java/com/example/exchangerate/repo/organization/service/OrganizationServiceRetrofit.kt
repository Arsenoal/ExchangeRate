package com.example.exchangerate.repo.organization.service

import com.example.exchangerate.repo.commonmodels.KeyValueResponse
import com.example.exchangerate.repo.commonmodels.OrganizationParams
import retrofit2.http.GET
import retrofit2.http.Query

interface OrganizationServiceRetrofit {
    @GET("branches.ashx")
    suspend fun getOrganizationParams(@Query("id") organizationId: String): KeyValueResponse<String, OrganizationParams>
}