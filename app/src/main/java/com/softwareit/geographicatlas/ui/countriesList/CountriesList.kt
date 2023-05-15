package com.softwareit.geographicatlas.ui.countriesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.softwareit.geographicatlas.data.remote.CountriesService
import com.softwareit.geographicatlas.databinding.FragmentCountriesListBinding
import com.softwareit.geographicatlas.ui.adapter.CountriesAdapter
import dagger.hilt.android.AndroidEntryPoint

/*class CountriesList : Fragment() {

      private val viewModel: CountriesListViewModel by viewModels(factoryProducer = {
          object : ViewModelProvider.Factory {
              override fun <T : ViewModel> create(modelClass: Class<T>): T {
                  return CountriesListViewModel(
                      application = requireActivity().application,

  //                    countryId = countryId
                  ) as T
              }
          }
      })
    private var _binding: FragmentCountriesListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountriesListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val countriesAdapter = CountriesAdapter()

        binding.countriesListRecyclerView.apply {
            adapter = countriesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.countries.observe(viewLifecycleOwner) {
            countriesAdapter.submitList(it)
        }

//        viewModel.data.observe(viewLifecycleOwner) { result ->
//            countriesAdapter.submitList(result.data?.data)
//            progressBar.isVisible = result is Resource.Loading && result.data?.data.isNullOrEmpty()
//            errorTextView.isVisible = result is Resource.Error && result.data?.data.isNullOrEmpty()
//            errorTextView.text = result.error?.localizedMessage
//        }
//    }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}*/

@AndroidEntryPoint
class CountriesList : Fragment() {

    private val viewModel: CountriesListViewModel by viewModels()
    private lateinit var countriesAdapter: CountriesAdapter
    private var _binding: FragmentCountriesListBinding? = null
    private val binding get() = _binding!!
    private lateinit var countriesService: CountriesService

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

        viewModel.remoteAllCountries.observe(viewLifecycleOwner) {
            countriesAdapter.submitList(it)
        }

    }

//    private fun readDatabase() {
//        lifecycleScope.launch{
//            CountriesListViewModel.readAllCharacters.observe(viewLifecycleOwner) { database ->
//                if (database.isNotEmpty()) {
//                    Log.d("CharactersFragment", "readDatabase called")
//                    mAdapter.setData(database[0].characterList)
//                    hideShimmerEffect()
//                } else {
//                    requestApiData()
//                }
//            }
//        }
//    }
//
//    companion object {
//        private const val TAG = "CountriesList"
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
