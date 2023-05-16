package com.softwareit.geographicatlas.ui.countryDetails

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.softwareit.geographicatlas.databinding.FragmentCountryDetailsBinding
import com.softwareit.geographicatlas.utils.getColoredText
import com.softwareit.geographicatlas.utils.loadImgUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryDetails : Fragment() {

    private val viewModel: CountryDetailsViewModel by viewModels()
    private var _binding: FragmentCountryDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountryDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.remoteCountry.observe(viewLifecycleOwner) {
            binding.apply {
                val country = it[0]
                loadImgUrl(flagIv, country.flags.png)
                capitalNameTv.text = getColoredText("&#8226; Capital: \n${country.capital?.get(0) ?: "No capital"}")
                capitalCoordinatesTv.text = getColoredText("&#8226; Capital Coordinates: \n${country.capitalInfo.latlng}")
                populationTv.text = getColoredText("&#8226; Population: \n${country.population}")
                areaTv.text = getColoredText("&#8226; Area: \n${country.area} km²")
                val currencyString =
                    country.currencies?.entries?.joinToString("\n") { "${it.value.name} (${it.value.symbol}) (${it.key})" }
                currenciesTv.text = getColoredText("&#8226; Currencies: \n$currencyString")
                regionTv.text = getColoredText("&#8226; Region: \n${country.subregion ?: "No region"}")
                timezonesTv.text = getColoredText("&#8226; Timezones: \n${country.timezones.joinToString("\n")}")
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}