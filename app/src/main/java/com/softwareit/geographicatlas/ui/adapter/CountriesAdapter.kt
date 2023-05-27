package com.softwareit.geographicatlas.ui.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.softwareit.geographicatlas.R
import com.softwareit.geographicatlas.databinding.CountryListItemBinding
import com.softwareit.geographicatlas.databinding.HeaderLayoutBinding
import com.softwareit.geographicatlas.ui.countriesList.CountriesListDirections
import com.softwareit.geographicatlas.ui.model.Country
import com.softwareit.geographicatlas.ui.model.RowItem
import com.softwareit.geographicatlas.utils.getColoredText
import com.softwareit.geographicatlas.utils.loadImgUrl


@RequiresApi(Build.VERSION_CODES.N)
class CountriesAdapter :
    PagingDataAdapter<RowItem, RecyclerView.ViewHolder>(CountryComparator()) {

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when (item) {
            is RowItem.CountryWrapper -> COUNTRY_ROW
            is RowItem.HeaderWrapper -> HEADER_ROW
            else -> throw IllegalArgumentException("Invalid item type at position $position")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            COUNTRY_ROW -> {
                val binding = CountryListItemBinding.inflate(inflater, parent, false)
                CountryViewHolder(binding)
            }

            HEADER_ROW -> {
                val binding = HeaderLayoutBinding.inflate(inflater, parent, false)
                HeaderViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = getItem(position)

        when (holder) {
            is CountryViewHolder -> {
                val rowItem = currentItem as? RowItem.CountryWrapper
                rowItem?.country?.let { holder.bind(it) }
            }

            is HeaderViewHolder -> {
                val headerWrapper = currentItem as? RowItem.HeaderWrapper
                headerWrapper?.continent?.let { holder.bind(it) }
            }
        }
    }

    class HeaderViewHolder(private val binding: HeaderLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(continent: String) {
            binding.headerTv.text = continent
        }
    }

    inner class CountryViewHolder(private val binding: CountryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(country: Country) {
            setupClickListener(country)
            bindCountryData(country)
            bindExpandableView(country)
            setupLearnMoreButton(country)
        }

        private fun setupClickListener(country: Country) {
            binding.rowLayout.setOnClickListener {
                country.isCollapsed = !country.isCollapsed
                notifyItemChanged(bindingAdapterPosition)
            }
        }

        private fun bindCountryData(country: Country) {
            binding.apply {
                loadImgUrl(flagIv, country.flags.png)
                countryNameTv.text = country.name.common
                capitalNameTv.text = country.capital?.get(0) ?: "NO capital"
                tvPopulation.text =
                    getColoredText("Population:${country.population}", "CountriesAdapter")
                tvArea.text = getColoredText("Area:${country.area} km²", "CountriesAdapter")
                val currencyString =
                    country.currencies?.entries?.joinToString("\n") { "${it.value.name} (${it.value.symbol}) (${it.key})" }
                tvCurrencies.text = getColoredText("Currencies:$currencyString", "CountriesAdapter")
            }
        }

        private fun bindExpandableView(country: Country) {
            binding.expandViewLayout.apply {
                if (country.isCollapsed) {
                    visibility = View.GONE
                    binding.dropdownIv.setImageResource(R.drawable.baseline_expand_more_24)
                } else {
                    visibility = View.VISIBLE
                    binding.dropdownIv.setImageResource(R.drawable.baseline_expand_less_24)
                }
            }
        }

        private fun setupLearnMoreButton(country: Country) {
            binding.learnMoreBtn.setOnClickListener {
                val countryCode = country.cca2
                val countryName = country.name.common
                val action = CountriesListDirections.actionCountriesListToCountryDetails(
                    countryCode,
                    countryName
                )
                findNavController(itemView).navigate(action)
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