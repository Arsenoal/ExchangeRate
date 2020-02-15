package com.example.exchangerate.repo.paymentvariants.service

import com.example.exchangerate.entity.Lang

interface PaymentVariantsProviderService {
    suspend fun getVariants(lang: Lang): List<String>
}