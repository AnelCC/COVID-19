package com.anelcc.coronavirustrack.ui.countrydetail

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.anelcc.coronavirustrack.R
import com.anelcc.coronavirustrack.api.ApiClient
import com.anelcc.coronavirustrack.databinding.ActivityCountryDetailsBinding
import com.anelcc.coronavirustrack.ui.countrydetail.DetailConstant.COUNTRY_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryDetailsActivity : AppCompatActivity() {
    lateinit var progerssProgressDialog: ProgressDialog
    private var binding: ActivityCountryDetailsBinding? = null
    lateinit var viewModel: CountryDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_country_details)
        viewModel = ViewModelProvider(this)[CountryDetailViewModel::class.java]
        binding!!.countryDetailViewModel = viewModel;

        if (getIntent() != null && getIntent().getStringExtra(COUNTRY_KEY) != null) {
            progerssProgressDialog=ProgressDialog(this)
            progerssProgressDialog.setTitle("Loading")
            progerssProgressDialog.setCancelable(false)
            progerssProgressDialog.show()
            getData(getIntent().getStringExtra(COUNTRY_KEY))
        }
    }

    private fun getData(country: String) {
        val call: Call<CountryDetail> = ApiClient.getClient.getCountry(country)
        call.enqueue(object : Callback<CountryDetail> {
            override fun onFailure(call: Call<CountryDetail>, t: Throwable) {
                Log.d("CountryDetailsActivity:", "fail response::: ${t.message}")
            }

            override fun onResponse(call: Call<CountryDetail>, response: Response<CountryDetail>) {
                Log.d("CountryDetailsActivity:", "success response::: ${response.body()}")
                val country: CountryDetail = response.body()!!
                //updateView(country)
                viewModel.setData(country)
                progerssProgressDialog.dismiss()
            }
        })
    }
}
