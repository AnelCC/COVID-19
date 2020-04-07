package com.anelcc.coronavirustrack.ui.countrydetail

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.Observable.OnPropertyChangedCallback
import androidx.databinding.PropertyChangeRegistry
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anelcc.coronavirustrack.ui.countrydetail.DetailConstant.EMPTY_STRING
import com.anelcc.coronavirustrack.utils.NumberFormated.numberFormat


class CountryDetailViewModel : ViewModel(), Observable {
    private val registry = PropertyChangeRegistry()

    var countryDetail: MutableLiveData<CountryDetail>? = null
        set(value) {
            if (field != value) {
                field = value
            }
        }

    fun getCountryDetail(): LiveData<CountryDetail>? {
        if (countryDetail == null) {
            countryDetail = MutableLiveData()
        }
        return countryDetail
    }

    fun setData(countryDetail: CountryDetail) {
        this.countryDetail = MutableLiveData(countryDetail)
        registry.notifyChange(this, BR.countryTittle)
        registry.notifyChange(this, BR.countryTodayCasesAmount)
        registry.notifyChange(this, BR.countryTodayDeathsAmount)
        registry.notifyChange(this, BR.countryTotalCasesAmount)
        registry.notifyChange(this, BR.countryTotalDeathsAmount)
    }

    @Bindable
    fun getCountryTittle(): String {
        val value = getCountryDetail()?.value?.country
        return if (value.isNullOrEmpty()) EMPTY_STRING else value
    }

    @Bindable
    fun getCountryTodayCasesAmount(): String {
        val value = getCountryDetail()?.value?.todayCases
        return if (value == null) EMPTY_STRING else numberFormat(value).toString()
    }

    @Bindable
    fun getCountryTodayDeathsAmount(): String {
        val value = countryDetail?.value?.todayDeaths
        return if (value == null) EMPTY_STRING else numberFormat(value).toString()
    }

    @Bindable
    fun getCountryTotalCasesAmount(): String {
        val value = countryDetail?.value?.cases
        return if (value == null) EMPTY_STRING else numberFormat(value).toString()
    }

    @Bindable
    fun getCountryTotalDeathsAmount(): String {
        val value = countryDetail?.value?.deaths
        return if (value == null) EMPTY_STRING else numberFormat(value).toString()
    }

    override fun addOnPropertyChangedCallback(callback: OnPropertyChangedCallback) {
        registry.add(callback);
    }
    override fun removeOnPropertyChangedCallback(callback: OnPropertyChangedCallback) {
        registry.remove(callback);
    }
}