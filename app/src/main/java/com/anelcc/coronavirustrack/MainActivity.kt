package com.anelcc.coronavirustrack

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anelcc.coronavirustrack.api.ApiClient
import com.anelcc.coronavirustrack.databinding.ActivityMainBinding
import com.anelcc.coronavirustrack.ui.country.CountriesViewModel
import com.anelcc.coronavirustrack.ui.country.Country
import com.anelcc.coronavirustrack.ui.country.CountryAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var progerssProgressDialog: ProgressDialog
    private var binding: ActivityMainBinding? = null
    private var countriesViewModel: CountriesViewModel? = null

    private var countryAdapter: CountryAdapter? = null
    private var countryList = ArrayList<Country>()
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        countriesViewModel = CountriesViewModel(countryList)

        recyclerView = binding!!.countryList
        recyclerView = findViewById(R.id.country_list)
        val layoutManager = LinearLayoutManager(this)
        recyclerView!!.setLayoutManager(layoutManager)

        countryAdapter = CountryAdapter(countriesViewModel!!.countryList)
        recyclerView!!.setAdapter(countryAdapter)

        progerssProgressDialog=ProgressDialog(this)
        progerssProgressDialog.setTitle("Loading")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()
        getData()

    }

    private fun getData() {
        val call: Call<List<Country>> = ApiClient.getClient.getCountries()
        call.enqueue(object : Callback<List<Country>> {

            override fun onResponse(call: Call<List<Country>>?, response: Response<List<Country>>?) {
                countryList.addAll(response!!.body()!!)
                countryAdapter = CountryAdapter(countriesViewModel!!.countryList)
                recyclerView!!.setAdapter(countryAdapter)

                progerssProgressDialog.dismiss()

            }

            override fun onFailure(call: Call<List<Country>>?, t: Throwable?) {
                progerssProgressDialog.dismiss()
            }
        })
    }

}
