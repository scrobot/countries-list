package com.github.scrobot.countrieslist

import android.annotation.TargetApi
import android.content.res.Configuration
import android.os.Build
import android.text.TextUtils
import java.util.*

class CountriesDataManager(private val config: Configuration) {

    /**
     * Assembling countries list from CountryCodeAggregator List
     *
     * @return countries list model
     * */
    fun loadCountries(): List<Country> {
        val countries = ArrayList<Country>()

        CountryCodeAggregator.phonesCountryCode.forEach { code ->
            val loc = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                getSystemLocale(config)
            else
                getSystemLocaleLegacy(config)

            val currentLocale = if (TextUtils.isEmpty(loc.country)) "EN" else loc.country

            val locale = Locale(currentLocale, code.countryCode)
            val country = locale.getDisplayCountry(locale)
            val flagUnicode = getFlagUnicode(code.countryCode)

            if (country.isNotBlank()) {
                countries.add(Country(code, country, flagUnicode))
            }
        }

        return countries
    }

    /**
     * converting ASCII into native unicode flag icon
     *
     * @param code country code, like "US"
     * @return flag in unicode
     * */
    private fun getFlagUnicode(code: String): CharSequence {
        val flagOffset = 0x1F1E6
        val asciiOffset = 0x41

        val firstChar = Character.codePointAt(code, 0) - asciiOffset + flagOffset
        val secondChar = Character.codePointAt(code, 1) - asciiOffset + flagOffset

        return StringBuffer().apply {
            append(Character.toChars(firstChar))
            append(Character.toChars(secondChar))
        }
    }

    /**
     * System locale getter for legacy version of android API
     *
     * @param config android resources configuration
     * @return Locale
     * */
    private fun getSystemLocaleLegacy(config: Configuration): Locale {
        return config.locale
    }

    /**
     * System locale getter for version of android API >= 24
     *
     * @param config android resources configuration
     * @return Locale
     * */
    @TargetApi(Build.VERSION_CODES.N)
    private fun getSystemLocale(config: Configuration): Locale {
        return config.locales.get(0)
    }

}