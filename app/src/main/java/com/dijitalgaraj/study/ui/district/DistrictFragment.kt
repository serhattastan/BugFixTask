package com.dijitalgaraj.study.ui.district

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dijitalgaraj.study.base.BaseActionState
import com.dijitalgaraj.study.base.BaseFragment
import com.dijitalgaraj.study.databinding.DistrictListFragmentBinding
import com.dijitalgaraj.study.models.BoardItems
import com.dijitalgaraj.study.models.BoardsClicks
import com.dijitalgaraj.study.ui.city.adapter.BoardAdapter

class DistrictFragment : BaseFragment<DistrictViewModel, DistrictListFragmentBinding>(
    DistrictViewModel::class.java
) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> DistrictListFragmentBinding =
        DistrictListFragmentBinding::inflate

    private var boardAdapter: BoardAdapter?=null

    override fun init() {
        super.init()
        getData()
        setupAdapter()
        setupClickListeners()
    }

    override fun renderActionState(actionState: BaseActionState?) {
        when (val state = actionState as? DistrictActionState) {
            DistrictActionState.Init -> Unit
            else -> {}
        }
    }

    private fun getData(){
        viewModel.place = arguments?.getParcelable("place")
        binding.txtTitleCity.text = viewModel.place?.title
        setupAdapter()
    }

    private fun setupAdapter(){
        boardAdapter = BoardAdapter {
            when(it){
                is BoardsClicks.ClickedDistrict->{
                }
            }

        }

        binding.rvBoard.adapter = boardAdapter
        boardAdapter?.submitList(listOf(BoardItems.DistrictItem(viewModel.place?.places)))
    }

    private fun setupClickListeners(){
        binding.apply {
            viewBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}