package com.example.pal.ui.homeScreens.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pal.R
import com.example.pal.data.models.Cat
import com.example.pal.data.models.Dog
import com.example.pal.databinding.FragmentSearchBinding
import com.example.pal.ui.MainActivityViewModel

import com.example.pal.util.Loading

import dagger.hilt.android.AndroidEntryPoint

import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared
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

        val bundle = Bundle()

        // set the recycler
        binding.searchRecycler.layoutManager = LinearLayoutManager(requireContext())

        if (activityViewModel.petType == "Dog") {
            binding.searchRecycler.adapter = SearchAdapter(object : SearchAdapter.DogsListener {

                override fun onPetClicked(index: Int, dog: Dog) {

                    bundle.putSerializable("dog", dog)

                    findNavController().navigate(R.id.action_searchFragment_to_singlePetInfo, bundle)
                }
            },null ,activityViewModel.petType)
        } else {
            binding.searchRecycler.adapter = SearchAdapter(null, object : SearchAdapter.CatsListener {

                override fun onPetClicked(index: Int, cat: Cat) {

                    bundle.putSerializable("cat", cat)

                    findNavController().navigate(R.id.action_searchFragment_to_singlePetInfo, bundle)
                }
            }, activityViewModel.petType)
        }


        // Observe the dogs LiveData
        viewModel.dogs.observe(viewLifecycleOwner) {

            when (it.status) {

                is Loading -> {
                    binding.searchPage.isVisible = false
                    binding.searchLoading.isVisible = true
                }

                is Success -> {
                    binding.searchPage.isVisible = true
                    binding.searchLoading.isVisible = false
                    (binding.searchRecycler.adapter as SearchAdapter).setDogs(it.status.data!!)
                }

                is Error -> {
                    Toast.makeText(requireContext(),"No such a breed", Toast.LENGTH_SHORT).show()
                    binding.searchLoading.isVisible = false
                    binding.searchPage.isVisible = false
                }

                else -> {}
            }
        }
    }
}