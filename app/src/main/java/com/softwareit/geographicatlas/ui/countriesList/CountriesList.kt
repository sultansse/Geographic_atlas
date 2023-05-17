package com.softwareit.geographicatlas.ui.countriesList

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.softwareit.geographicatlas.databinding.FragmentCountriesListBinding
import com.softwareit.geographicatlas.ui.adapter.CountriesAdapter
import com.softwareit.geographicatlas.ui.model.RowItem
import com.softwareit.geographicatlas.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@RequiresApi(Build.VERSION_CODES.N)
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
        setupRecyclerView()
        observeCountryData()
    }

    private fun setupRecyclerView() {
        countriesAdapter = CountriesAdapter()
        binding.countriesListRecyclerView.apply {
            adapter = countriesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeCountryData() {
        viewModel.remoteAllCountries.observe(viewLifecycleOwner) { status ->
            when (status) {
                is Resource.Loading -> showLoadingState()
                is Resource.Success -> showDataState(status.data)
                else -> showErrorState()
            }
        }
    }

    private fun showLoadingState() {
        binding.shimmerFrameLayout.startShimmer()
    }

    private fun showDataState(data: List<RowItem>?) {
        binding.shimmerFrameLayout.stopShimmer()
        binding.shimmerFrameLayout.visibility = View.GONE
        if (data != null) {
            binding.countriesListRecyclerView.visibility = View.VISIBLE
            countriesAdapter.submitList(data)
        }
    }

    private fun showErrorState() {
        binding.shimmerFrameLayout.stopShimmer()
        binding.shimmerFrameLayout.visibility = View.GONE
        binding.errorTv.visibility = View.VISIBLE
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
