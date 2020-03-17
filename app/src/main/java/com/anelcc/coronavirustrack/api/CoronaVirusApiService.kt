package com.anelcc.coronavirustrack.api

import com.anelcc.coronavirustrack.ui.country.Country
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface CoronaVirusApiService {
    @GET("countries")
    fun getCountries(): Call<List<Country>>

    /*@GET("countries/{country}")
    fun getCountry(@Path("country") country: String?): Call<List<Repo?>?>?*/
}