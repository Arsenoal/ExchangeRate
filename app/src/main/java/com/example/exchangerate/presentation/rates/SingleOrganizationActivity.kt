package com.example.exchangerate.presentation.rates

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exchangerate.R
import com.example.exchangerate.common.empty
import com.example.exchangerate.entity.rates.BuySell
import com.example.exchangerate.entity.rates.Currency
import com.example.exchangerate.presentation.BaseActivity
import com.example.exchangerate.presentation.rates.adapter.CurrencyRecyclerAdapter
import com.example.exchangerate.presentation.rates.adapter.model.CurrencyParcelable
import kotlinx.android.synthetic.main.activity_single_organization.*
import org.koin.androidx.viewmodel.ext.android.viewModel

const val ORGANIZATION_ID_EXTRA = "organization_id_extra"
const val ORGANIZATION_NAME_EXTRA = "organization_name_extra"
const val ORGANIZATION_AVAILABLE_CURRENCIES_EXTRA = "organization_available_currencies_extra"

class SingleOrganizationActivity : BaseActivity() {

    private val singleOrganizationViewModel: SingleOrganizationViewModel by viewModel()

    private val adapter = CurrencyRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_organization)

        setupViewModel()

        setupRecyclerView()
    }

    private fun setupViewModel() {
        singleOrganizationViewModel.organizationParamsAcquiredLiveData.observe(this, Observer {
            it.run {
                bankNameTextView.text = organizationName
                bankCityLocationTextView.text = organizationCityLocation
                bankCityAddressLocationTextView.text = organizationAddress
                organizationPhoneNumberTextView.text = organizationContacts

                workingHours.forEachIndexed{ index, pair ->
                    when(index) {
                        0 -> {
                            workingHourFirstKeyTextView.text = pair.first
                            workingHourFirstValueTextView.text = pair.second
                        }
                        1 -> {
                            workingHourSecondKeyTextView.text = pair.first
                            workingHourSecondValueTextView.text = pair.second
                        }
                    }
                }
            }
        })

        singleOrganizationViewModel.organizationParamsAcquisitionFailLiveData.observe(this, Observer {

        })

        val organizationIdExtra = intent.extras?.getString(ORGANIZATION_ID_EXTRA) ?: String.empty()
        val organizationNameExtra = intent.extras?.getString(ORGANIZATION_NAME_EXTRA) ?: String.empty()

        singleOrganizationViewModel.loadOrganizationParams(organizationIdExtra, organizationNameExtra)
    }

    private fun setupRecyclerView() {

        val currenciesParcelableListExtra = intent.extras?.getParcelableArrayList<CurrencyParcelable>(ORGANIZATION_AVAILABLE_CURRENCIES_EXTRA)

        currenciesParcelableListExtra?.run {
            adapter.currencies = map {
                Currency(
                    title = it.title,
                    zero = BuySell(buy = it.buy, sell = it.sell),
                    one = BuySell())
            }
        }

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        currencyListRecyclerView.layoutManager = layoutManager
        currencyListRecyclerView.adapter = adapter
    }

}
