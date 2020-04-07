package com.anelcc.coronavirustrack.utils

import java.text.NumberFormat
import java.util.*

object NumberFormated{

    fun numberFormat(number: Int) = NumberFormat.getNumberInstance(Locale.getDefault()).format(number)
}