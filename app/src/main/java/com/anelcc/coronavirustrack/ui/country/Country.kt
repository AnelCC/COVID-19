package com.anelcc.coronavirustrack.ui.country

data class Country(
    val country: String? = null,
    val cases: Int? = null,
    val todayCases: Int? = null,
    val deaths: Int? = null,
    val todayDeaths: Int? = null,
    val recovered: Int? = null,
    val active: Int? = null,
    val critical: Int? = null
)