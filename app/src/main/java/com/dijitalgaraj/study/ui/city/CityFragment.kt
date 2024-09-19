package com.dijitalgaraj.study.ui.city

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.dijitalgaraj.study.R
import com.dijitalgaraj.study.base.BaseActionState
import com.dijitalgaraj.study.base.BaseFragment
import com.dijitalgaraj.study.databinding.CityListFragmentBinding
import com.dijitalgaraj.study.models.loadPlacesFromJson
import com.dijitalgaraj.study.ui.city.adapter.CityAdapter
import com.dijitalgaraj.study.ui.district.DistrictFragmentArgs
import com.dijitalgaraj.study.utils.extensions.OnTextChangeListener
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CityFragment : BaseFragment<CityViewModel, CityListFragmentBinding>(
    CityViewModel::class.java
) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> CityListFragmentBinding =
        CityListFragmentBinding::inflate

    private var cityListAdapter: CityAdapter? = null
    private var textChangeListener: OnTextChangeListener? = null

    override fun init() {
        super.init()
        readData()
        setupEditTextListener()
        setupClickListeners()
    }

    override fun renderActionState(actionState: BaseActionState?) {
        when (val state = actionState as? CityActionState) {
            CityActionState.Init -> Unit
            else -> {}
        }
    }

    private fun readData() {
        viewModel.baseCityList = loadPlacesFromJson(requireContext())
        setupAdapter()
    }

    private fun setupEditTextListener() {
        textChangeListener = object : OnTextChangeListener {
            override fun onTextChanged(text: String) {
                viewModel.search(text)
                binding.imgDelete.isVisible = text.isNotEmpty()
            }
        }

        viewModel.filteredCityList.observe(viewLifecycleOwner) { filteredList ->
            cityListAdapter?.submitList(filteredList)
        }

    }

    private fun setupAdapter() {
        cityListAdapter = CityAdapter {
            if (viewModel.selectedItem != null) {
                navigateDetail()
            }
            viewModel.selectedItem = it
        }

        binding.rvCities.adapter = cityListAdapter
        cityListAdapter?.submitList(viewModel.baseCityList)
    }

    private fun setupClickListeners() {
        binding.apply {
            imgDelete.setOnClickListener {
                binding.edtSearchBar.setText("")
            }
        }
    }


    @OptIn(DelicateCoroutinesApi::class)
    fun navigateDetail() {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                val data = DistrictFragmentArgs(viewModel.selectedItem)
                findNavController().navigate(R.id.districtFragment, data.toBundle())
           }
        }

    }


}
