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
import com.softwareit.geographicatlas.utils.Resource
import com.softwareit.geographicatlas.utils.getColoredText
import com.softwareit.geographicatlas.utils.loadImgUrl
import dagger.hilt.android.AndroidEntryPoint

@RequiresApi(Build.VERSION_CODES.N)
@AndroidEntryPoint
class CountryDetails : Fragment() {

    private val viewModel: CountryDetailsViewModel by viewModels()
    private var _binding: FragmentCountryDetailsBinding? = null
    private val binding get() = _binding!!
    private var mapLink: String? = null

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

        (requireActivity() as AppCompatActivity).supportActionBar?.title = countryName

        viewModel.onLoadCountryDetails(countryCode)

        viewModel.remoteCountry.observe(viewLifecycleOwner) { status ->
            when (status) {
                is Resource.Loading -> showLoadingState()
                is Resource.Success -> showDataState(status.data)
                else -> showErrorState()
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
        mapLink = null
    }

    private fun onRemoteCountryChanged(countries: List<CountryNetworkModel>) {
        val country = countries.first()
        country.let {
            mapLink = it.maps.googleMaps
            bindCountryDetails(country)
        }
    }

    private fun showLoadingState() {
        binding.shimmerFrameLayout.startShimmer()
    }

    private fun showDataState(data: List<CountryNetworkModel>?) {
        binding.shimmerFrameLayout.stopShimmer()
        binding.shimmerFrameLayout.visibility = View.GONE
        binding.countryDetailsLayout.visibility = View.VISIBLE
        onRemoteCountryChanged(data!!)
    }

    private fun showErrorState() {
        binding.shimmerFrameLayout.stopShimmer()
        binding.shimmerFrameLayout.visibility = View.GONE
        binding.errorTv.visibility = View.VISIBLE
    }

    private fun bindCountryDetails(country: CountryNetworkModel) {
        loadImgUrl(binding.flagIv, country.flags.png)

        val capital = country.capital?.getOrNull(0) ?: "No capital"
        binding.capitalNameTv.text = getColoredText("&#9679; Capital:$capital", "CountryDetails")
        val capitalCoordinates = getColoredText(
            "&#9679; Capital Coordinates:${country.capitalInfo.latlng}",
            "CountryDetails"
        )
        binding.capitalCoordinatesTv.text = capitalCoordinates

        val populationText =
            getColoredText("&#9679; Population:${country.population}", "CountryDetails")
        val areaText = getColoredText("&#9679; Area:${country.area} km²", "CountryDetails")
        binding.populationTv.text = populationText
        binding.areaTv.text = areaText

        val currencyString =
            country.currencies?.entries?.joinToString("\n") { "${it.value.name} (${it.value.symbol}) (${it.key})" }
        binding.currenciesTv.text =
            getColoredText("&#9679; Currencies:$currencyString", "CountryDetails")

        binding.regionTv.text =
            getColoredText("&#9679; Region:${country.subregion ?: "No region"}", "CountryDetails")
        binding.timezonesTv.text = getColoredText(
            "&#9679; Timezones:${country.timezones.joinToString("\n")}",
            "CountryDetails"
        )
    }
}