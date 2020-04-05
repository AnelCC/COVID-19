package com.anelcc.coronavirustrack.ui.country

import androidx.lifecycle.ViewModel
import com.anelcc.coronavirustrack.utils.NumberFormated.numberFormat
import com.anelcc.coronavirustrack.model.Country


class CountriesViewModel(var countries: ArrayList<Country>) : ViewModel(){

    val countryList: ArrayList<CountryViewModel>
        get() {
            val auxCountryList: ArrayList<CountryViewModel> = ArrayList()
            for (country in countries) {
                country!!.cases?.let { numberFormat(it) }?.let {
                    CountryViewModel(
                        country.country.toString(),
                        it,
                        country.countryInfo?.flag.toString()
                    )
                }?.let {
                    auxCountryList.add(
                        it
                    )
                }
            }
            return auxCountryList;
        }
}
