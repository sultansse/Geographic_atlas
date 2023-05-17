package com.softwareit.geographicatlas.ui.countriesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.softwareit.geographicatlas.databinding.FragmentCountriesListBinding
import com.softwareit.geographicatlas.ui.adapter.CountriesAdapter
import com.softwareit.geographicatlas.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountriesList : Fragment() {

    private val viewModel: CountriesListViewModel by viewModels()
    private var _binding: FragmentCountriesListBinding? = null
    private val binding get() = _binding!!
    private lateinit var countriesAdapter: CountriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountriesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        countriesService = RetrofitInstance.retrofit.create(CountriesService::class.java)
        countriesAdapter = CountriesAdapter()

        binding.countriesListRecyclerView.apply {
            adapter = countriesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }


        viewModel.remoteAllCountries.observe(viewLifecycleOwner) { status ->
            when (status) {
                is Resource.Loading -> {
                    binding.shimmerFrameLayout.startShimmer()
                }

                is Resource.Success -> {
                    binding.shimmerFrameLayout.stopShimmer()
                    binding.shimmerFrameLayout.visibility = View.GONE
                    status.data?.let {
                        binding.countriesListRecyclerView.visibility = View.VISIBLE
                        countriesAdapter.submitList(it)
                    }
                }

                else -> {
                    binding.shimmerFrameLayout.stopShimmer()
                    binding.shimmerFrameLayout.visibility = View.GONE
                    binding.errorTv.visibility = View.VISIBLE
                }
            }

        }

    }

    override fun onResume() {
        super.onResume()
        binding.shimmerFrameLayout.startShimmer()
    }

    override fun onPause() {
        binding.shimmerFrameLayout.stopShimmer()
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}