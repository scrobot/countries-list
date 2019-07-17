package com.github.scrobot.sampleapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.scrobot.countrieslist.Country
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter: RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    private val countries = mutableListOf<Country>()

    fun loadCountries(list: List<Country>) {
        countries.clear()
        countries.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false))
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        fun bind(country: Country) {
            view.vFlag.text = country.flagCode
            view.vName.text = country.countryName
            view.vCode.text = country.code.countryCode
            view.vPhoneCode.text = "+${country.code.phoneCode}"
        }

    }

}