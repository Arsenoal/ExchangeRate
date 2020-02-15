package com.example.exchangerate.presentation.common.adapter

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.example.exchangerate.R
import com.example.exchangerate.presentation.common.adapter.model.SpinnerWithIconItem

class SpinnerWithIconAdapter(
        private val mContext: Context,
        private val resourceId: Int,
        private val items: List<SpinnerWithIconItem>): ArrayAdapter<SpinnerWithIconItem>(mContext, resourceId, items) {

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getIconView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getIconView(position, convertView, parent)
    }

    private fun getIconView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater = LayoutInflater.from(mContext)
        val rowView = layoutInflater.inflate(resourceId, parent, false)

        val currencyIconView = rowView.findViewById<AppCompatImageView>(R.id.currencyIconView)
        val currencyTextView = rowView.findViewById<AppCompatTextView>(R.id.currencyTextView)

        items[position].run {
            currencyTextView.text = text
            currencyIconView.setImageDrawable(ContextCompat.getDrawable(context, resId))
        }

        return rowView
    }
}