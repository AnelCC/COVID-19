package com.anelcc.coronavirustrack.model

data class CovidCases (
    val cases: Int? = null,
    val todayCases: Int? = null,
    val deaths: Int? = null,
    val todayDeaths: Int? = null,
    val recovered: Int? = null,
    val active: Int? = null,
    val critical: Int? = null,
    val casesPerOneMillion: Int? = null,
    val deathsPerOneMillion: Double? = null,
    var tests: Int? = null,
    var testsPerOneMillion: Double? = null,
    var affectedCountries: Int? = null){}
