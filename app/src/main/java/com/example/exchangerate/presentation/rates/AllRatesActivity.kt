package com.example.exchangerate.presentation.rates

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exchangerate.R
import com.example.exchangerate.common.presentation.ActivityNavigator
import com.example.exchangerate.presentation.BaseActivity
import com.example.exchangerate.presentation.rates.adapter.OrganizationsRecyclerAdapter
import com.example.exchangerate.presentation.rates.adapter.model.CurrencyParcelable
import com.example.exchangerate.presentation.rates.adapter.model.OrganizationAdapterModel
import com.example.exchangerate.presentation.rates.adapter.model.OrganizationRatesModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class AllRatesActivity : BaseActivity() {

    private val allRatesViewModel: AllRatesViewModel by viewModel()

    private lateinit var adapter: OrganizationsRecyclerAdapter

    private lateinit var organizations: List<OrganizationRatesModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRatesViewModel()
    }

    override fun onResume() {
        super.onResume()

        setupClickChannelForAdapter()

        setupRecyclerView()

    }

    private fun setupClickChannelForAdapter() {
        val clickChannel = Channel<OrganizationAdapterModel>()

        adapter = OrganizationsRecyclerAdapter(clickChannel)

        CoroutineScope(GlobalScope.coroutineContext).launch {
            val item = clickChannel.receive()

            val extras = Bundle()
            extras.putString(ORGANIZATION_ID_EXTRA, item.organizationId)
            extras.putString(ORGANIZATION_NAME_EXTRA, item.organizationName)

            val list: List<CurrencyParcelable>? = organizations.find {
                it.organizationAdapterModel.organizationId == item.organizationId
            }?.run {
                currencies.map { currency ->
                    currency.run {
                        CurrencyParcelable(
                            title = title,
                            buy = zero.buy,
                            sell = zero.sell
                        )
                    }
                }
            }

            val currenciesParcelable = ArrayList<CurrencyParcelable>()
            list?.forEach {
                currenciesParcelable.add(it)
            }

            extras.putParcelableArrayList(ORGANIZATION_AVAILABLE_CURRENCIES_EXTRA, currenciesParcelable)

            ActivityNavigator.navigate(this@AllRatesActivity, SingleOrganizationActivity::class.java, extras)
        }
    }

    private fun setupRatesViewModel() {
        allRatesViewModel.ratesAcquiredLiveData.observe(this, Observer { organizations ->
            this@AllRatesActivity.organizations = organizations
            adapter.list = organizations.map {
                it.organizationAdapterModel
            }
        })

        allRatesViewModel.ratesAcquisitionFailedLiveData.observe(this, Observer {

        })

        allRatesViewModel.currenciesAcquiredLiveData.observe(this, Observer {
            //TODO replace with spinner/dropdown
            exchangeCurrencyTextView.text = it[0]
            allRatesViewModel.loadRatesByCurrency(it[0])
        })

        allRatesViewModel.currenciesAcquisitionFailedLiveData.observe(this, Observer {

        })
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        banksListRecyclerView.layoutManager = layoutManager

        banksListRecyclerView.adapter = adapter

        allRatesViewModel.loadAvailableCurrencies()
    }
}
