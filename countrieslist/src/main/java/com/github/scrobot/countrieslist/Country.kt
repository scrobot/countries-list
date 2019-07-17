package com.github.scrobot.countrieslist

data class Country(val code: CountryCode,
                   val countryName: String,
                   val flagCode: CharSequence? = null)