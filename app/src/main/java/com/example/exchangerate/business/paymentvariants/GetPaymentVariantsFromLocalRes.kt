package com.example.exchangerate.business.paymentvariants

import com.example.exchangerate.entity.Lang
import com.example.exchangerate.repo.paymentvariants.PaymentVariantsProviderRepo

class GetPaymentVariantsFromLocalRes(
        private val paymentVariantsProviderRepo: PaymentVariantsProviderRepo
): GetPaymentVariantsUseCase {
    override suspend fun getVariants(lang: Lang) = with(paymentVariantsProviderRepo) {
        getPaymentVariants(lang)
    }
}