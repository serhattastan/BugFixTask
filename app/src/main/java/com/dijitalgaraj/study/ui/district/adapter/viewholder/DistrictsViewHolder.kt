package com.dijitalgaraj.study.ui.district.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.dijitalgaraj.study.databinding.BoardViewHolderBinding
import com.dijitalgaraj.study.models.BoardsClicks
import com.dijitalgaraj.study.ui.district.adapter.DistrictAdapter

class DistrictsViewHolder (private val binding: BoardViewHolderBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private lateinit var adapter: DistrictAdapter

    fun bind(items: List<String?>?, onClickItem: (BoardsClicks) -> Unit) {
        adapter = DistrictAdapter {
            onClickItem.invoke(BoardsClicks.ClickedDistrict(it))
        }
        binding.rvDistricts.adapter = adapter
        adapter.submitList(items)

    }
}