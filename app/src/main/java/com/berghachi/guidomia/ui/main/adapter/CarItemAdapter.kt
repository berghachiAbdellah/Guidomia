package com.berghachi.guidomia.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.berghachi.guidomia.R
import com.berghachi.guidomia.databinding.CarItemBinding
import com.berghachi.guidomia.domain.model.CarItem
import com.berghachi.guidomia.utils.coolNumberFormat
import com.berghachi.guidomia.utils.hide
import com.berghachi.guidomia.utils.loadImage
import com.berghachi.guidomia.utils.show


class CarItemAdapter :
    ListAdapter<CarItem, CarItemAdapter.CarsItemViewHolder>(
        DiffCallback
    ) {
    private var previousExpandedPosition = 0
    private var mExpandedPosition = 0

    class CarsItemViewHolder(var binding: CarItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(car: CarItem) {
            binding.txtCarTitle.text = "${car.make} ${car.model}"
            binding.txtCarPrice.text = binding.root.context.getString(R.string.price) + car.marketPrice.coolNumberFormat()
            binding.ratingBarCar.rating = car.rating.toFloat()
            binding.ivCarPicture.loadImage(car.make)

            if (car.prosList.isEmpty()) {
                binding.recyclerPros.hide()
                binding.txtTitlePros.hide()
            } else {
                binding.recyclerPros.adapter =
                    DetailCarItemAdapter(car.prosList.filter { it.isNotBlank() })
                binding.recyclerPros.show()
                binding.txtTitlePros.show()
            }

            if (car.consList.isEmpty()) {
                binding.recyclerCons.hide()
                binding.txtTitleCons.hide()
            } else {
                binding.recyclerCons.adapter =
                    DetailCarItemAdapter(car.consList.filter { it.isNotBlank() })
                binding.recyclerCons.show()
                binding.txtTitleCons.show()
            }
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
        holder.bind(cars)
        val isExpanded = position == mExpandedPosition
        holder.binding.csDetail.visibility = if (isExpanded) View.VISIBLE else View.GONE
        holder.itemView.isActivated = isExpanded

        if (isExpanded) previousExpandedPosition = position

        holder.itemView.setOnClickListener {
            mExpandedPosition = if (isExpanded) -1 else position
            notifyItemChanged(previousExpandedPosition)
            notifyItemChanged(position)
        }


    }

}
