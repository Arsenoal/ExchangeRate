package com.example.exchangerate.presentation.organizations.organizations

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exchangerate.R
import com.example.exchangerate.common.presentation.ActivityNavigator
import com.example.exchangerate.common.presentation.FragmentNavigator
import com.example.exchangerate.common.presentation.view.appendToolBarHeight
import com.example.exchangerate.common.presentation.view.slideDown
import com.example.exchangerate.common.toArrayAdapter
import com.example.exchangerate.common.toSpinnerWithIconItems
import com.example.exchangerate.presentation.base.FullScreenActivity
import com.example.exchangerate.presentation.common.entity.ERSuccess
import com.example.exchangerate.presentation.common.navigator.NavigatorFragment
import com.example.exchangerate.presentation.common.navigator.NavigatorViewModel
import com.example.exchangerate.presentation.organizations.ORGANIZATION_AVAILABLE_CURRENCIES_EXTRA
import com.example.exchangerate.presentation.organizations.ORGANIZATION_ID_EXTRA
import com.example.exchangerate.presentation.organizations.ORGANIZATION_NAME_EXTRA
import com.example.exchangerate.presentation.organizations.SingleOrganizationActivity
import com.example.exchangerate.presentation.organizations.organizations.adapter.OrganizationsRecyclerAdapter
import com.example.exchangerate.presentation.organizations.adapter.model.CurrencyParcelable
import com.example.exchangerate.presentation.organizations.organizations.adapter.model.OrganizationAdapterModel
import com.example.exchangerate.presentation.organizations.organizations.adapter.model.OrganizationRatesModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_progress.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrganizationActivity : FullScreenActivity() {

    private val organizationsViewModel: OrganizationsViewModel by viewModel()

    val navigatorViewModel: NavigatorViewModel by viewModel()

    private lateinit var adapter: OrganizationsRecyclerAdapter

    private lateinit var organizations: List<OrganizationRatesModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigator()

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

            ActivityNavigator.navigate(this@OrganizationActivity, SingleOrganizationActivity::class.java, extras)
        }
    }

    private fun setupRatesViewModel() {

        organizationsViewModel.ratesStateLiveData.observe(this, Observer { state ->
            when(state.status) {
                ERSuccess -> {
                    organizations = state.result

                    adapter.list = organizations.map { it.organizationAdapterModel }

                    layoutProgressContainerView.slideDown()
                }
                else -> {
                    //TODO show error or retry
                    layoutProgressContainerView.slideDown()
                }
            }
        })

        organizationsViewModel.currenciesStateLiveData.observe(this, Observer { state ->
            when(state.status) {
                ERSuccess -> {
                    state.result.let { list ->
                        currenciesView.adapter = list.toSpinnerWithIconItems().run {
                            toArrayAdapter(this@OrganizationActivity, R.layout.layout_currency_spinner_item)
                        }
                        organizationsViewModel.loadRatesByCurrency(list[0])
                    }
                }
                else -> {
                    //TODO show error
                }
            }
        })

        organizationsViewModel.paymentVariantsStateLiveData.observe(this, Observer { state ->
            when(state.status) {
                ERSuccess -> {
                    paymentVariantsView.adapter = state.result.toArrayAdapter(this, R.layout.layout_payment_type_spinner_item)
                }
                else -> {
                    //TODO show error
                }
            }
        })

        organizationsViewModel.loadPaymentVariants()

    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        banksListRecyclerView.layoutManager = layoutManager

        banksListRecyclerView.adapter = adapter

        organizationsViewModel.loadAvailableCurrencies()
    }

    private fun setupNavigator() {

        navBarFragmentContainer.appendToolBarHeight()

        FragmentNavigator.addFragment(this, NavigatorFragment.newInstance(), navBarFragmentContainer.id)

        navigatorViewModel.onItemSelectedLiveData.observe(this, Observer {
            //TODO fetch data from api(if there is any api)
        })
    }
}
