package com.anelcc.coronavirustrack.ui.country

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel
import com.anelcc.coronavirustrack.ui.countrydetail.DetailConstant
import com.anelcc.coronavirustrack.utils.NumberFormated

class CountryViewModel(val country: String, val cases: String, val flag: String): ViewModel(), Observable {
    private val registry = PropertyChangeRegistry()

    @Bindable
    fun getCasesAmount(): String {
        return if (cases == null) DetailConstant.EMPTY_STRING else NumberFormated.numberFormat(cases.toInt()).toString()
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        registry.add(callback);
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        registry.remove(callback);
    }
}