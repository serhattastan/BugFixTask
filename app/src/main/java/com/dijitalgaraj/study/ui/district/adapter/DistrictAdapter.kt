package com.dijitalgaraj.study.ui.district.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dijitalgaraj.study.databinding.DistrictItemBinding

class DistrictAdapter (private val onClickItem:(String) -> Unit) :
    ListAdapter<String, DistrictAdapter.DistrictAdapterViewHolder>(
        DistrictAdapterComparator
    ) {
    inner class DistrictAdapterViewHolder(private val binding: DistrictItemBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(item: String) {
            binding.apply {
                txtDistrictName.text = item
                root.setOnClickListener {

                    onClickItem.invoke(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DistrictAdapterViewHolder {
        val mView = DistrictItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DistrictAdapterViewHolder(mView)
    }

    override fun onBindViewHolder(holder: DistrictAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DistrictAdapterComparator = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }
}