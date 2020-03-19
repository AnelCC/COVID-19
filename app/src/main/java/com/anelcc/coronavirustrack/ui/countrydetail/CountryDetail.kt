package com.anelcc.coronavirustrack.ui.countrydetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CountryDetail {
    @SerializedName("country")
    @Expose
    var country: String? = null
    @SerializedName("cases")
    @Expose
    var cases: Int? = null
    @SerializedName("todayCases")
    @Expose
    var todayCases: Int? = null
    @SerializedName("deaths")
    @Expose
    var deaths: Int? = null
    @SerializedName("todayDeaths")
    @Expose
    var todayDeaths: Int? = null
    @SerializedName("recovered")
    @Expose
    var recovered: Int? = null
    @SerializedName("active")
    @Expose
    var active: Int? = null
    @SerializedName("critical")
    @Expose
    var critical: Int? = null
    @SerializedName("casesPerOneMillion")
    @Expose
    var casesPerOneMillion: Int? = null

}
