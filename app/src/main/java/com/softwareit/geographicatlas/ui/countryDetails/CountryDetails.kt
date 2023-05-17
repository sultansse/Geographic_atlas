package com.softwareit.geographicatlas.ui.countryDetails

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.softwareit.geographicatlas.data.remote.model.CountryNetworkModel
import com.softwareit.geographicatlas.databinding.FragmentCountryDetailsBinding
import com.softwareit.geographicatlas.utils.getColoredText
import com.softwareit.geographicatlas.utils.loadImgUrl
import dagger.hilt.android.AndroidEntryPoint

@RequiresApi(Build.VERSION_CODES.N)
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

        val args: CountryDetailsArgs by navArgs()
        val countryCode = args.countryCode
        val countryName = args.countryName
        lateinit var mapLink: String

        (requireActivity() as AppCompatActivity).supportActionBar?.title = countryName

        viewModel.onViewCreatedCountryCode(countryCode)

        viewModel.remoteCountry.observe(viewLifecycleOwner) { countries ->
            if (countries.isNotEmpty()) {
                val country = countries[0]
                mapLink = country.maps.googleMaps
                bindCountryDetails(country)
            }
        }

        binding.capitalCoordinatesTv.setOnClickListener {
            Toast.makeText(requireContext(), "Opening Google Maps", Toast.LENGTH_SHORT).show()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mapLink))
            startActivity(intent)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bindCountryDetails(country: CountryNetworkModel) {
        loadImgUrl(binding.flagIv, country.flags.png)
        binding.capitalNameTv.text =
            getColoredText("&#9679; Capital:${country.capital?.get(0) ?: "No capital"}", "CountryDetails")
        binding.capitalCoordinatesTv.text =
            getColoredText("&#9679; Capital Coordinates:${country.capitalInfo.latlng}", "CountryDetails")
        binding.populationTv.text = getColoredText("&#9679; Population:${country.population}", "CountryDetails")
        binding.areaTv.text = getColoredText("&#9679; Area:${country.area} km²", "CountryDetails")
        val currencyString =
            country.currencies?.entries?.joinToString("\n") { "${it.value.name} (${it.value.symbol}) (${it.key})" }
        binding.currenciesTv.text = getColoredText("&#9679; Currencies:$currencyString", "CountryDetails")
        binding.regionTv.text =
            getColoredText("&#9679; Region:${country.subregion ?: "No region"}", "CountryDetails")
        binding.timezonesTv.text =
            getColoredText("&#9679; Timezones:${country.timezones.joinToString("\n")}", "CountryDetails")
    }

}