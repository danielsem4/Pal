package com.example.pal.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pal.R

class HomeAdapter : RecyclerView.Adapter<HomeViewHolder>() {

    // the data list of the recycler
    private var dataList = mutableListOf<HomeDataItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {

        // check which view we are and inflate the correct one
        val layout = when(viewType) {
            VIEW_HEADER ->  R.layout.home_header
            VIEW_ITEMS -> R.layout.home_item
            else -> 0
        }

        // inflate the chosen layout
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return HomeViewHolder(view)

    }

    // set the values from the data list to the holder
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    // get the view type (if its header value = 0, for item value = 1)
    override fun getItemViewType(position: Int): Int {
        return when(dataList[position]) {
            is HomeDataItem.HomeHeader -> VIEW_HEADER
            is HomeDataItem.HomeItem -> VIEW_ITEMS
        }
    }

    override fun getItemCount() = dataList.size

    // set new items to the data list
    fun setItems(data: List<HomeDataItem>) {
        dataList.apply {
            clear()
            addAll(data)
        }
    }

    // set value for every view
    companion object {
        const val VIEW_HEADER = 0
        const val VIEW_ITEMS = 1
    }

}