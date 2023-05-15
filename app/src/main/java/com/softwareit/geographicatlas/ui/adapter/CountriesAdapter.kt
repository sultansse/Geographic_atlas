package com.softwareit.geographicatlas.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.softwareit.geographicatlas.R
import com.softwareit.geographicatlas.data.model.Country
import com.softwareit.geographicatlas.data.model.CountryItem
import com.softwareit.geographicatlas.databinding.CountryListItemBinding
import com.softwareit.geographicatlas.utils.loadImgUrl

class CountriesAdapter : ListAdapter<CountryItem, CountriesAdapter.CountryViewHolder>(CountryComporator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding =
            CountryListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(countryHolder: CountryViewHolder, headerHolder: HeaderViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            countryHolder.bind(currentItem)
            headerHolder.bind(currentItem)
        }

    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(continent: String) {
//            binding.apply {
//                headerTv.text = continent
//            }
        }

    }

    class CountryViewHolder(private val binding: CountryListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(country: Country) {
            binding.apply {

                loadImgUrl(flagIv, country.flags.png)
                countryNameTv.text = country.name.common
                capitalNameTv.text = "Capital: ${country?.capital?.get(0) ?: "NO capital"}"
                tvPopulation.text = "Population: ${country.population}"
                tvArea.text = "Area: ${country.area} km²"
                tvCurrencies.text = "Currencies: 0000"

                // Set click listener to expand or collapse the view
                cardLayout.setOnClickListener {
                    if (expandedView.visibility == View.GONE) {
                        // Expand the view
                        expandedView.visibility = View.VISIBLE
                        dropdownIv.setImageResource(R.drawable.baseline_expand_more_24)
                    } else {
                        // Collapse the view
                        expandedView.visibility = View.GONE
                        dropdownIv.setImageResource(R.drawable.baseline_expand_less_24)
                    }
                }
            }
        }
    }

    class CountryComporator : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Country, newItem: Country) =
            oldItem == newItem
    }
}