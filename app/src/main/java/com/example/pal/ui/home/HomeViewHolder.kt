package com.example.pal.ui.home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pal.R

class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    // assigning the data inside the holder for the header (set the title and the describe)
    private fun bindHeader(data : HomeDataItem.HomeHeader) {

        val title: TextView = itemView.findViewById(R.id.header_title)
        val description: TextView = itemView.findViewById(R.id.header_description)

        title.text = data.title
        description.text = data.description

    }

    // assigning the data inside the holder for the item (set the name the describe and the image)
    private fun bindItem(data : HomeDataItem.HomeItem) {

        val pic: ImageView = itemView.findViewById(R.id.item_pic)
        val name: TextView = itemView.findViewById(R.id.item_name)
        val description: TextView = itemView.findViewById(R.id.item_description)

        pic.setImageResource(data.image)
        name.text = data.name
        description.text = data.description

    }

    // check if the data is Header or Item
    fun bind(data: HomeDataItem) {
        when(data) {
            is HomeDataItem.HomeHeader -> bindHeader(data)
            is HomeDataItem.HomeItem -> bindItem(data)
        }
    }

}