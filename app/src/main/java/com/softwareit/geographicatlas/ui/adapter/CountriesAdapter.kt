package com.softwareit.geographicatlas.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.softwareit.geographicatlas.R
import com.softwareit.geographicatlas.databinding.CountryListItemBinding
import com.softwareit.geographicatlas.ui.model.Country
import com.softwareit.geographicatlas.ui.model.RowItem
import com.softwareit.geographicatlas.utils.loadImgUrl

class CountriesAdapter :
    ListAdapter<RowItem, RecyclerView.ViewHolder>(CountryComparator()) {

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when (item) {
            is RowItem.CountryWrapper -> COUNTRY_ROW
            is RowItem.HeaderWrapper -> HEADER_ROW
            else -> throw IllegalArgumentException("Invalid item type at position $position")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            COUNTRY_ROW -> {
                val binding =
                    CountryListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                CountryViewHolder(binding)
            }

            HEADER_ROW -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.header_layout, parent, false)
                HeaderViewHolder(view)
            }

            else -> throw IllegalArgumentException("Invalid view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = getItem(position)

        when (holder) {
            is CountryViewHolder -> {
                val rowItem = currentItem as? RowItem.CountryWrapper
                rowItem?.let {
                    holder.bind(it.country)
                }
            }

            is HeaderViewHolder -> {
                val headerWrapper = currentItem as? RowItem.HeaderWrapper
                headerWrapper?.let {
                    holder.bind(it.continents)
                }
            }
        }
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val headerTv: TextView = itemView.findViewById(R.id.header_tv)

        fun bind(continent: String) {
            headerTv.text = continent
        }
    }

    class CountryViewHolder(private val binding: CountryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(country: Country) {
            binding.apply {
                // Bind the country data to the views in your layout
                loadImgUrl(flagIv, country.flags.png)
                countryNameTv.text = country.name.common
                capitalNameTv.text = country.capital?.get(0) ?: "NO capital"
                tvPopulation.text = "Population: ${country.population}"
                tvArea.text = "Area: ${country.area} km²"
                val currencyString =
                    country.currencies?.entries?.joinToString("\n") { "${it.value.name} (${it.value.symbol}) (${it.key})" }
                tvCurrencies.text = "Currencies: $currencyString"

                // Set click listener to expand or collapse the view
                cardLayout.setOnClickListener {
                    with(expandedView) {
                        if (visibility == View.GONE) {
                            // Expand the view
                            visibility = View.VISIBLE
                            dropdownIv.setImageResource(R.drawable.baseline_expand_less_24)
                        } else {
                            // Collapse the view
                            visibility = View.GONE
                            dropdownIv.setImageResource(R.drawable.baseline_expand_more_24)
                        }
                    }
                }

            }
        }
    }

    class CountryComparator : DiffUtil.ItemCallback<RowItem>() {
        override fun areItemsTheSame(oldItem: RowItem, newItem: RowItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RowItem, newItem: RowItem): Boolean {
            return oldItem == newItem
        }
    }

    companion object {
        private const val HEADER_ROW = 0
        private const val COUNTRY_ROW = 1
    }
}