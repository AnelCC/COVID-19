package com.anelcc.coronavirustrack.ui.country

import androidx.lifecycle.ViewModel


class CountriesViewModel(var countries: ArrayList<Country>) : ViewModel(){

    val countryList: ArrayList<CountryViewModel>
        get() {
            val auxCountryList: ArrayList<CountryViewModel> = ArrayList()
            for (country in countries) {
                auxCountryList.add(
                    CountryViewModel(
                        country.country.toString(),
                        country.cases.toString()
                    )
                )
            }
            return auxCountryList;
        }
}
