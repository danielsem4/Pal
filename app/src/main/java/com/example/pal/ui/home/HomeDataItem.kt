package com.example.pal.ui.home

// the recycler view items, the header contains the company description.
// and the items contain the dogs / cats that are for adoption.

sealed class HomeDataItem {

    data class HomeHeader(val title: String, val description: String) : HomeDataItem()

    data class HomeItem(val image: Int, val name: String, val description: String) : HomeDataItem()

}