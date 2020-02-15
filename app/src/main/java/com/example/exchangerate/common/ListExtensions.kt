package com.example.exchangerate.common

import android.content.Context
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import com.example.exchangerate.entity.rates.Currency

fun List<Currency>.getCurrencyByName(currencyName: String): Currency? {
    return find { it.title == currencyName }
}

fun List<String>.toArrayAdapter(context: Context, @LayoutRes itemResId: Int): ArrayAdapter<CharSequence> {
    return ArrayAdapter(context, itemResId, this)
}