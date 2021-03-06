package com.nicomahnic.bitnovochallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicomahnic.bitnovochallenge.R
import com.nicomahnic.bitnovochallenge.models.Window

class WindowAdapter(
    private var windowList: MutableList<Window>,
) : RecyclerView.Adapter<WindowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WindowViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context )
        return WindowViewHolder(layoutInflater.inflate(R.layout.item_window, parent, false))
    }

    override fun onBindViewHolder(holder: WindowViewHolder, position: Int) {
        val item = windowList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = windowList.size
    
}