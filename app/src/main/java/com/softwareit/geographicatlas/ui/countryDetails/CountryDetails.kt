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
        val mapLink = "https://goo.gl/maps/4F4PpDhGJgVvLby57"

        (requireActivity() as AppCompatActivity).supportActionBar?.title = countryName

        viewModel.onViewCreatedCountryCode(countryCode)

        viewModel.remoteCountry.observe(viewLifecycleOwner) {
            binding.apply {
                val country = it[0]
                loadImgUrl(flagIv, country.flags.png)
                capitalNameTv.text =
                    getColoredText("&#8226; Capital: ${country.capital?.get(0) ?: "No capital"}")
                capitalCoordinatesTv.text =
                    getColoredText("&#8226; Capital Coordinates: ${country.capitalInfo.latlng}")
                populationTv.text = getColoredText("&#8226; Population: ${country.population}")
                areaTv.text = getColoredText("&#8226; Area: ${country.area} km²")
                val currencyString =
                    country.currencies?.entries?.joinToString("\n") { "${it.value.name} (${it.value.symbol}) (${it.key})" }
                currenciesTv.text = getColoredText("&#8226; Currencies: $currencyString")
                regionTv.text =
                    getColoredText("&#8226; Region: ${country.subregion ?: "No region"}")
                timezonesTv.text =
                    getColoredText("&#8226; Timezones: ${country.timezones.joinToString("\n")}")
            }
        }

        binding.capitalCoordinatesTv.setOnClickListener {
            Toast.makeText(requireContext(), "Opening Google Maps", Toast.LENGTH_SHORT).show()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mapLink))
            intent.setPackage("com.google.android.apps.maps") // Specify the package name for Google Maps
            startActivity(intent)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}