package com.berghachi.guidomia.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.berghachi.guidomia.R
import com.berghachi.guidomia.databinding.CarItemBinding
import com.berghachi.guidomia.databinding.DetailCarItemBinding
import com.berghachi.guidomia.domain.model.CarItem
import com.berghachi.guidomia.utils.coolNumberFormat
import com.berghachi.guidomia.utils.hide
import com.berghachi.guidomia.utils.loadImage
import com.berghachi.guidomia.utils.show


class CarItemAdapter(private val carList: List<CarItem>?) :
    RecyclerView.Adapter<CarItemAdapter.CarsItemViewHolder>() {
    private var previousExpandedPosition = 0
    private var mExpandedPosition = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarsItemViewHolder {
        return CarsItemViewHolder(
            CarItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CarsItemViewHolder, position: Int) {
        val cars = carList?.get(position)
        cars?.let {
            holder.bind(it)
            toggleItem(position, holder)
        }
    }

    private fun toggleItem(
        position: Int,
        holder: CarsItemViewHolder
    ) {
        val isExpanded = position == mExpandedPosition
        holder.binding.csDetail.visibility = if (isExpanded) View.VISIBLE else View.GONE
        holder.itemView.isActivated = isExpanded
        if (isExpanded) previousExpandedPosition = position
        if (position == ((carList?.size ?: 0) - 1)
        ) holder.binding.separator.hide() else holder.binding.separator.show()

        holder.itemView.setOnClickListener {
            mExpandedPosition = if (isExpanded) -1 else position
            notifyItemChanged(previousExpandedPosition)
            notifyItemChanged(position)
        }
    }

    class CarsItemViewHolder(var binding: CarItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(car: CarItem) {
            binding.txtCarTitle.text = "${car.make} ${car.model}"
            binding.txtCarPrice.text =
                binding.root.context.getString(R.string.price, car.marketPrice.coolNumberFormat())
            binding.ratingBarCar.rating = car.rating.toFloat()
            binding.ivCarPicture.loadImage(car.make)


            if (car.prosList.isEmpty()) {
                binding.llPros.hide()
                binding.txtTitlePros.hide()
            } else {
                fillDetail(binding.llPros,car.prosList.filter { it.isNotBlank() })
                binding.llPros.show()
                binding.txtTitlePros.show()
            }

            if (car.consList.isEmpty()) {
                binding.llCons.hide()
                binding.txtTitleCons.hide()
            } else {
                fillDetail(binding.llCons,car.consList.filter { it.isNotBlank() })
                binding.llCons.show()
                binding.txtTitleCons.show()
            }
        }

        private fun fillDetail(linearLayout: LinearLayout, pros: List<String>) {
            linearLayout.removeAllViews()
            pros.forEach {
                val childView = DetailCarItemBinding.inflate(LayoutInflater.from(linearLayout.context), null,false)
                childView.txtDescription.text = it
                linearLayout.addView(childView.root)
            }
        }
    }

    override fun getItemCount(): Int {
        return carList?.size ?: 0
    }

}
