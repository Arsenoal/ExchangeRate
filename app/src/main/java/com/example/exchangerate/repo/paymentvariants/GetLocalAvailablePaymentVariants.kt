package com.example.exchangerate.repo.paymentvariants

import com.example.exchangerate.entity.Lang
import com.example.exchangerate.repo.paymentvariants.service.PaymentVariantsProviderService

class GetLocalAvailablePaymentVariants(
        private val paymentVariantsProviderService: PaymentVariantsProviderService
): PaymentVariantsProviderRepo {
    override suspend fun getPaymentVariants(lang: Lang) = with(paymentVariantsProviderService) {
        getVariants(lang)
    }
}