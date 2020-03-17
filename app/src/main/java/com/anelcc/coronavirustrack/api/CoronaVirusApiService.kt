package com.anelcc.coronavirustrack.api

import com.anelcc.coronavirustrack.ui.Country
import retrofit2.Call
import retrofit2.http.GET


interface CoronaVirusApiService {
    @GET("countries")
    fun getCountries(): Call<List<Country>>


}