package com.berghachi.guidomia.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.berghachi.guidomia.databinding.CarItemBinding
import com.berghachi.guidomia.domain.model.CarItem
import com.berghachi.guidomia.utils.coolNumberFormat
import com.berghachi.guidomia.utils.loadImage


class CarItemAdapter(private val onClickListener: (CarItem) -> Unit,) :
    ListAdapter<CarItem, CarItemAdapter.CarsItemViewHolder>(
        DiffCallback
    ) {

    class CarsItemViewHolder(private var binding: CarItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(car: CarItem) {
            binding.txtCarTitle.text = "${car.make} ${car.model}"
            binding.txtCarPrice.text = "Price: ${car.marketPrice.coolNumberFormat()}"
            binding.ratingBarCar.rating = car.rating.toFloat()
            binding.ivCarPicture.loadImage(car.make)
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CarItem>() {
        override fun areItemsTheSame(oldItem: CarItem, newItem: CarItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CarItem, newItem: CarItem): Boolean {
            return oldItem == newItem
        }
    }

    /**
     * Override on create view holder
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarsItemViewHolder {
        return CarsItemViewHolder(
            CarItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    override fun onBindViewHolder(holder: CarsItemViewHolder, position: Int) {
        val cars = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener(cars)
        }

        holder.bind(cars)
    }

}
