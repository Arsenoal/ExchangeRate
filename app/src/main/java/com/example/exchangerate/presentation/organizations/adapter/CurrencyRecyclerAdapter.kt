package com.example.exchangerate.presentation.organizations.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerate.R
import com.example.exchangerate.entity.rates.Currency

class CurrencyRecyclerAdapter: RecyclerView.Adapter<CurrencyRecyclerAdapter.ViewHolder>() {

    var currencies: List<Currency> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val currencyNameTextView = view.findViewById<AppCompatTextView>(R.id.currencyNameTextView)
        val buyTextView = view.findViewById<AppCompatTextView>(R.id.buyTextView)
        val sellTextView = view.findViewById<AppCompatTextView>(R.id.sellTextView)

        fun bind(currency: Currency) {
            currency.run {
                currencyNameTextView.text = title
                buyTextView.text = zero.buy
                sellTextView.text = zero.sell
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.currency_recycler_item, parent, false))
    }

    override fun getItemCount() = currencies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currencies[position])
    }
}