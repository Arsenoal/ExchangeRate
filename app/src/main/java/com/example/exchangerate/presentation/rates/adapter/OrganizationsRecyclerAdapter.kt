package com.example.exchangerate.presentation.rates.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerate.R
import com.example.exchangerate.presentation.rates.adapter.model.OrganizationAdapterModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class OrganizationsRecyclerAdapter(
    private val clickChannel: Channel<OrganizationAdapterModel>
): RecyclerView.Adapter<OrganizationsRecyclerAdapter.ViewHolder>() {

    var list: List<OrganizationAdapterModel> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val organizationIconImageView = view.findViewById<AppCompatImageView>(R.id.organizationIconImageView)
        val bankNameTextView = view.findViewById<AppCompatTextView>(R.id.bankNameTextView)
        val distanceFromBankTextView = view.findViewById<AppCompatTextView>(R.id.distanceFromBankTextView)
        val buyTextView = view.findViewById<AppCompatTextView>(R.id.buyTextView)
        val sellTextView = view.findViewById<AppCompatTextView>(R.id.sellTextView)

        fun bind(organization: OrganizationAdapterModel) {
            organization.run {
                bankNameTextView.text = organizationName
                distanceFromBankTextView.text = distance
                buyTextView.text = buy
                sellTextView.text = sell
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.organization_recycler_item, parent, false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.bind(item)

        holder.itemView.setOnClickListener {
            CoroutineScope(GlobalScope.coroutineContext).launch {
                clickChannel.send(item)
            }
        }
    }
}