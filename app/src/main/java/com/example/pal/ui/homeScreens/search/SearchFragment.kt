package com.example.pal.ui.homeScreens.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.pal.R
import com.example.pal.databinding.FragmentSearchBinding
import com.example.pal.ui.MainActivityViewModel

import com.example.pal.util.Loading

import com.example.pal.ui.homeScreens.home.HomeAdapter
import com.example.pal.ui.signin.LoginViewModel
import com.google.api.ResourceProto.resource

import dagger.hilt.android.AndroidEntryPoint

import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared
import com.example.pal.util.Resource
import com.example.pal.util.Success

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var binding : FragmentSearchBinding by autoCleared()

    private val viewModel : SearchViewModel by viewModels()

    // the activity viewModel
    private val activityViewModel : MainActivityViewModel by activityViewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBinding.inflate(inflater, container, false)

        // get the dogs info from the firebase
        viewModel.getDogs()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set the recycler
        binding.searchRecycler.layoutManager = LinearLayoutManager(requireContext())

        binding.searchRecycler.adapter = SearchAdapter(object : SearchAdapter.PetsListener {

            override fun onPetClicked(index: Int) {
                TODO("Not yet implemented")
            }
        }, activityViewModel.petType)


        // Observe the dogs LiveData
        viewModel.dogs.observe(viewLifecycleOwner) {


            when (it) {

                is Resource.Loading -> {
                    binding.searchPage.isVisible = false
                    binding.searchLoading.isVisible = true
                }

                is Resource.Success -> {
                    binding.searchPage.isVisible = true
                    binding.searchLoading.isVisible = false
                    (binding.searchRecycler.adapter as SearchAdapter).setDogs(it.data!!)
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(),it.message, Toast.LENGTH_SHORT).show()
                    binding.searchLoading.isVisible = false
                    binding.searchPage.isVisible = false
                }


            }
        }
    }
}