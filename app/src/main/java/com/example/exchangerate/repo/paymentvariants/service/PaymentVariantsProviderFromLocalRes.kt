package com.example.exchangerate.repo.paymentvariants.service

import android.content.Context
import com.example.exchangerate.R
import com.example.exchangerate.entity.AM
import com.example.exchangerate.entity.EN
import com.example.exchangerate.entity.Lang
import com.example.exchangerate.entity.RU

class PaymentVariantsProviderFromLocalRes(
        private val context: Context
): PaymentVariantsProviderService {
    override suspend fun getVariants(lang: Lang) = with(context.resources) {
        when(lang) {
            EN -> getStringArray(R.array.paymentVariantsEn)
            RU -> getStringArray(R.array.paymentVariantsRu)
            AM -> getStringArray(R.array.paymentVariantsAm)
        }.toList()
    }
}