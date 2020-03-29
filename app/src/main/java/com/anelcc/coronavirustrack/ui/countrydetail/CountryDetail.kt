package com.anelcc.coronavirustrack.ui.countrydetail

data class CountryDetail (
    var country: String? = null,
    var cases: Int? = null,
    var todayCases: Int? = null,
    var deaths: Int? = null,
    var todayDeaths: Int? = null,
    var recovered: Int? = null,
    var active: Int? = null,
    var critical: Int? = null,
    var casesPerOneMillion: Double? = null
)
