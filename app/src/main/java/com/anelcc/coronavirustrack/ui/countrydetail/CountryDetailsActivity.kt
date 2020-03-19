package com.anelcc.coronavirustrack.ui.countrydetail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.anelcc.coronavirustrack.R
import com.anelcc.coronavirustrack.api.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_details)
        if (getIntent() != null && getIntent().getStringExtra("COUNTRY_KEY") != null) {
            getData(getIntent().getStringExtra("COUNTRY_KEY"))
        }
    }

    private fun getData(country: String) {
        val call: Call<List<CountryDetail>> = ApiClient.getClientCovid.getCountry(country)
        call.enqueue(object : Callback<List<CountryDetail>> {
            override fun onFailure(call: Call<List<CountryDetail>>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<List<CountryDetail>>, response: Response<List<CountryDetail>>
            ) {
                Log.d("CountryDetailsActivity:", "response::: ${response.message()}")
            }

        })
    }

}
