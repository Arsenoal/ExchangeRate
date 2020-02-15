package com.example.exchangerate.repo.paymentvariants

import com.example.exchangerate.entity.Lang

interface PaymentVariantsProviderRepo {
    suspend fun getPaymentVariants(lang: Lang): List<String>
}