package com.anelcc.coronavirustrack

import android.app.ProgressDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anelcc.coronavirustrack.api.ApiClient
import com.anelcc.coronavirustrack.databinding.ActivityMainBinding
import com.anelcc.coronavirustrack.model.Country
import com.anelcc.coronavirustrack.model.CovidCases
import com.anelcc.coronavirustrack.ui.country.CountriesViewModel
import com.anelcc.coronavirustrack.ui.country.CountryAdapter
import com.anelcc.coronavirustrack.ui.country.CountryViewModel
import com.anelcc.coronavirustrack.utils.NumberFormated.numberFormat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var progerssProgressDialog: ProgressDialog
    private var binding: ActivityMainBinding? = null
    private var countriesViewModel: CountriesViewModel? = null

    private var countryAdapter: CountryAdapter? = null
    private var covidCases = CovidCases()
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        countriesViewModel = CountriesViewModel(ArrayList<Country>())

        binding!!.btnCountry.setOnClickListener(this)
        binding!!.btnCases.setOnClickListener(this)
        binding!!.btnDeaths.setOnClickListener(this)

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
        getDataBySort("countries")

        binding!!.searchBar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var filteredList: ArrayList<CountryViewModel> = ArrayList()
                if (p0.toString() != "") {
                    for (item in countriesViewModel!!.countriesList.value!! ) {
                        if (item.country!!.toLowerCase().contains(p0.toString().toLowerCase())) {
                            filteredList.add(item)
                        }
                    }
                    countryAdapter = CountryAdapter(filteredList)
                } else {
                    countryAdapter = CountryAdapter(countriesViewModel!!.countriesList.value!!)
                }
                recyclerView!!.adapter = countryAdapter
            }

        })
    }

    private fun getData() {
        val call: Call<CovidCases> = ApiClient.getClient.getAll()
        call.enqueue(object : Callback<CovidCases> {
            override fun onFailure(call: Call<CovidCases>, t: Throwable) {
                Log.i("MainActivity","response"+  t.message)
                //countriesViewModel.setData(country)
                progerssProgressDialog.dismiss()
            }

            override fun onResponse(call: Call<CovidCases>, response: Response<CovidCases>) {
                Log.i("MainActivity", "response"+ response!!.body()!!.toString())
                covidCases = response!!.body()!!
                binding!!.totalCasesTitleAmount.text = covidCases.cases?.let { numberFormat(it).toString() }
                binding !!. notifyChange ()
                progerssProgressDialog . dismiss ()
            }
        })
    }

    private fun getDataBySort(country: String) {
        val call: Call<List<Country>> = ApiClient.getClient.getSort(country)
        call.enqueue(object : Callback<List<Country>> {
            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                Log.d("MainActivity:", "fail response::: ${t.message}")
            }

            override fun onResponse(call:Call<List<Country>>, response: Response<List<Country>>) {
                Log.d("MainActivity:", "success response::: ${response.body()}")
                countriesViewModel!!.countriesData.value = response.body()
                val auxCountryList: ArrayList<CountryViewModel> = ArrayList()
                countriesViewModel!!.countriesData.observe(this@MainActivity, Observer {
                    it.iterator().forEach {  auxCountryList.add(CountryViewModel(it.country!!, it.cases.toString()!!, it.countryInfo!!.flag!!))}
                    countriesViewModel!!.countriesList.postValue(auxCountryList)
                    countryAdapter = CountryAdapter(auxCountryList)
                    recyclerView!!.adapter = countryAdapter
                })

                progerssProgressDialog.dismiss()
            }
        })
    }

    override fun onClick(v: View?) {
        progerssProgressDialog.show()
        when (v!!.id) {
            R.id.btn_country -> {
                getDataBySort("countries")
            }
            R.id.btn_cases -> {
                getDataBySort("cases")
            }
            R.id.btn_deaths -> {
                getDataBySort("deaths")
            }
        }
    }
}
