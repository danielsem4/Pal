package com.example.pal.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pal.R
import com.example.pal.databinding.FragmentHomeBinding
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared

class HomeFragment : Fragment() {

    private var binding : FragmentHomeBinding by autoCleared()

    private var dataAdapter: HomeAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // set the adapter to our HomeAdapter
        dataAdapter = HomeAdapter()

        //
        binding.homeRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dataAdapter
        }

        // set the info to the recycler
        dataAdapter?.setItems(mockData())

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    // temp
    private fun mockData(): List<HomeDataItem> = listOf(
        HomeDataItem.HomeHeader(title = "Welcome", description = "those pets are waiting for you!"),
        HomeDataItem.HomeItem(name = "Kimi", description = "fasdfasdfasdfasdfasdfasfasdf", image = R.drawable.test),
        HomeDataItem.HomeItem(name = "Tedi", description = "sdfalkjhfsda", image = R.drawable.test),
        HomeDataItem.HomeItem(name = "Tedi", description = "sadfasdfsafasdfsadf", image = R.drawable.test),
        HomeDataItem.HomeItem(name = "Tedi", description = "sdfalkjhfsda", image = R.drawable.test),
        HomeDataItem.HomeItem(name = "Tedi", description = "asdfasdfasdfasdfsadf", image = R.drawable.test),
        HomeDataItem.HomeItem(name = "Tedi", description = "sdfalkjhfsda", image = R.drawable.test),
        HomeDataItem.HomeItem(name = "Tedi", description = "asdfasdfasdfasdfasdf", image = R.drawable.test),
        HomeDataItem.HomeItem(name = "Tedi", description = "sdfalkjhfsda", image = R.drawable.test),
        HomeDataItem.HomeItem(name = "Tedi", description = "asdfasdfasdfasdfasdfasdf", image = R.drawable.test),
        HomeDataItem.HomeItem(name = "Tedi", description = "sdfalkjhfsda", image = R.drawable.test),
        HomeDataItem.HomeItem(name = "Tedi", description = "sdfalkjhfsda", image = R.drawable.test),
        HomeDataItem.HomeItem(name = "tedi", description = "sdfalkjhfsda", image = R.drawable.test)

    )
}