package com.github.scrobot.sampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.scrobot.countrieslist.CountriesDataManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(vCountries) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = CountryAdapter().apply {
                loadCountries(CountriesDataManager(resources.configuration).loadCountries())
            }
        }
    }
}
