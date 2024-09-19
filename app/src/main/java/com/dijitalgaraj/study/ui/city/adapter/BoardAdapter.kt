package com.dijitalgaraj.study.ui.city.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dijitalgaraj.study.databinding.BoardViewHolderBinding
import com.dijitalgaraj.study.databinding.EmptyViewHolderBinding
import com.dijitalgaraj.study.models.BoardItems
import com.dijitalgaraj.study.models.BoardsClicks
import com.dijitalgaraj.study.ui.district.adapter.viewholder.DistrictsViewHolder
import com.dijitalgaraj.study.ui.district.adapter.viewholder.EmptyViewHolder

class BoardAdapter(private val onClickItem: (BoardsClicks) -> Unit) :
    ListAdapter<BoardItems, RecyclerView.ViewHolder>(BoardAdapterComparator) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is BoardItems.DistrictItem -> Companion.ViewType.DistrictsItem.ordinal
            is BoardItems.Empty -> Companion.ViewType.Empty.ordinal
            else -> throw UnsupportedOperationException("Unexpected View")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Companion.ViewType.DistrictsItem.ordinal -> {
                val mView = BoardViewHolderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                DistrictsViewHolder(mView)
            }
            Companion.ViewType.Empty.ordinal -> {
                val mView = EmptyViewHolderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                EmptyViewHolder(mView)
            }
            else -> throw UnsupportedOperationException("Unexpected View")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position).let {
            when (it) {
                is BoardItems.DistrictItem -> (holder as? DistrictsViewHolder)?.bind(
                    it.items, onClickItem
                )

                BoardItems.Empty -> (holder as? EmptyViewHolder)?.bind()
                else -> {}
            }
        }
    }

    companion object {
        private enum class ViewType {
            DistrictsItem,Empty
        }

        private val BoardAdapterComparator = object : DiffUtil.ItemCallback<BoardItems>() {
            override fun areItemsTheSame(oldItem: BoardItems, newItem: BoardItems): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: BoardItems, newItem: BoardItems): Boolean {
                return oldItem == newItem
            }
        }
    }
}