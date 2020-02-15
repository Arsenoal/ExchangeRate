package com.example.exchangerate.common

import android.content.Context
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import com.example.exchangerate.R
import com.example.exchangerate.entity.rates.Currency
import com.example.exchangerate.presentation.common.adapter.SpinnerWithIconAdapter
import com.example.exchangerate.presentation.common.adapter.model.SpinnerWithIconItem

fun List<Currency>.getCurrencyByName(currencyName: String): Currency? {
    return find { it.title == currencyName }
}

fun List<String>.toArrayAdapter(context: Context, @LayoutRes itemResId: Int): ArrayAdapter<CharSequence> {
    return ArrayAdapter(context, itemResId, this)
}

fun List<String>.toSpinnerWithIconItems(): List<SpinnerWithIconItem> {
    return map {
        SpinnerWithIconItem(text = it, resId = R.drawable.ic_united_states_flag)
    }
}

fun List<SpinnerWithIconItem>.toArrayAdapter(context: Context, @LayoutRes itemResId: Int): SpinnerWithIconAdapter {
    return SpinnerWithIconAdapter(context, itemResId, this)
}