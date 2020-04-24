package com.anelcc.coronavirustrack.repository

import com.anelcc.coronavirustrack.model.Country
import com.anelcc.coronavirustrack.model.CovidCases
import com.anelcc.coronavirustrack.ui.countrydetail.CountryDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface CoronaVirusApiService {

    // https://corona.lmao.ninja/countries
    @GET("all")
    fun getAll(): Call<CovidCases>

    // https://corona.lmao.ninja/countries
    @GET("countries")
    fun getCountries(): Call<List<Country>>

    // https://corona.lmao.ninja/countries/{country}
    @GET("countries/{country}")
    fun getCountry(@Path("country") country: String?): Call<CountryDetail>

    // https://disease.sh/v2/countries?sort=[property]
    //eg. cases, todayCases, deaths, todayDeaths, recovered, critical, 02-02-2020
    @GET("countries")
    fun getSort(@Query("sort") sort: String?): Call<List<Country>>
}