package com.example.exchangerate.business.paymentvariants

import com.example.exchangerate.entity.Lang

interface GetPaymentVariantsUseCase {
    suspend fun getVariants(lang: Lang): List<String>
}