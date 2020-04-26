package com.anelcc.coronavirustrack.ui.country

import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anelcc.coronavirustrack.model.Country
import com.anelcc.coronavirustrack.utils.NumberFormated.numberFormat


class CountriesViewModel(var countries: ArrayList<Country>) : ViewModel(), Observable {
    //instance of the class MutableLiveData
    val countriesData = MutableLiveData<List<Country>>()
    val countriesList = MutableLiveData<ArrayList<CountryViewModel>>()
    var cases: String = ""
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

    fun setCases(cases: Int) {
        this.cases = cases.toString()
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
