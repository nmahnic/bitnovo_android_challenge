package com.nicomahnic.bitnovochallenge.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nicomahnic.bitnovochallenge.R
import com.nicomahnic.bitnovochallenge.databinding.ItemWindowBinding
import com.nicomahnic.bitnovochallenge.models.Window

class WindowViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private var binding = ItemWindowBinding.bind(view)

    fun render(window: Window){
        binding.tvWindowNumber.text = window.id.toString()
        window.visitor?.let {
            binding.tvVisitorNumber.text = it.braceletNumber.toString()
            binding.tvVisitorNumber.visibility = View.VISIBLE
            binding.imgVisitor.visibility = View.VISIBLE
        } ?: run {
            binding.tvVisitorNumber.visibility = View.GONE
            binding.imgVisitor.visibility = View.GONE
        }

        if(window.rightPanel){
            binding.rightPanel.setBackgroundColor(itemView.context.getColor(R.color.teal_200))
        } else {
            binding.rightPanel.setBackgroundColor(itemView.context.getColor(R.color.wood))
        }

        if(window.leftPanel){
            binding.leftPanel.setCardBackgroundColor(itemView.context.getColor(R.color.teal_200))
        } else {
            binding.leftPanel.setCardBackgroundColor(itemView.context.getColor(R.color.wood))
        }

    }
}