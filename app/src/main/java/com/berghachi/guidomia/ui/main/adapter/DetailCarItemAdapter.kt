package com.berghachi.guidomia.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.berghachi.guidomia.databinding.DetailCarItemBinding

class DetailCarItemAdapter(
    private val details: List<String>
) : RecyclerView.Adapter<DetailCarItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DetailCarItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val detail = details[position]
        holder.description.text = detail
    }

    override fun getItemCount(): Int = details.size

    inner class ViewHolder(binding: DetailCarItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val description: TextView = binding.txtDescription
    }

}
