package com.dijitalgaraj.study.ui.city.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dijitalgaraj.study.R
import com.dijitalgaraj.study.databinding.CityItemBinding
import com.dijitalgaraj.study.models.Place

class CityAdapter (private val onClickItem:(Place) -> Unit) :
    ListAdapter<Place, CityAdapter.CityAdapterViewHolder>(
        CityAdapterComparator
    ) {

    inner class CityAdapterViewHolder(private val binding: CityItemBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(item: Place) {
            binding.apply {
                txtCityName.text = item.title
                txtDistrictCount.text = itemView.context.getString(R.string.district_count, item.places.size.toString())
                txtColorName.text = item.colorName

                card.backgroundTintList = ColorStateList.valueOf(Color.parseColor(item.color))

                root.setOnClickListener {
                    onClickItem.invoke(item)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityAdapterViewHolder {
        val mView = CityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityAdapterViewHolder(mView)
    }

    override fun onBindViewHolder(holder: CityAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val CityAdapterComparator = object : DiffUtil.ItemCallback<Place>() {
            override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
                return oldItem == newItem
            }
        }
    }
}