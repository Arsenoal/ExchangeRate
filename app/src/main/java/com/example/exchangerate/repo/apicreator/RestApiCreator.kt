package com.example.exchangerate.repo.apicreator

import com.example.exchangerate.repo.apicreator.entity.ApiProviderConfig
import com.example.exchangerate.repo.apicreator.gson.gsonModule
import com.example.exchangerate.repo.apicreator.okhttp.okhttpModule
import com.example.exchangerate.repo.apicreator.retorift.retrofitModule
import org.koin.core.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.core.inject
import retrofit2.Retrofit

class RestApiCreator(apiProviderConfig: ApiProviderConfig): KoinComponent {

    private val retrofit: Retrofit by inject()

    init {
        loadKoinModules(listOf(gsonModule, okhttpModule(apiProviderConfig), retrofitModule(apiProviderConfig)))
    }

    fun<API_SERVICE_INTERFACE> createApiService(apiClass: Class<API_SERVICE_INTERFACE>): API_SERVICE_INTERFACE = retrofit.create(apiClass)

}