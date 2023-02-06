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
import com.example.pal.R
import com.example.pal.databinding.FragmentSearchBinding
import com.example.pal.ui.MainActivityViewModel
import com.example.pal.util.Loading
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

        val parameters = this.resources.getStringArray(R.array.parameters)

        binding = FragmentSearchBinding.inflate(inflater, container, false)

        // check what pet the user choose and if he made the search fun
        if (activityViewModel.petType == "Dog" && viewModel.searchState) {

            binding.sizeRatingBar.Parameters.text = parameters[3]
            binding.sizeRatingBar.ratingBar.rating = viewModel.dog.size.toFloat()

            binding.droolingRatingBar.Parameters.text = parameters[4]
            binding.droolingRatingBar.ratingBar.rating = viewModel.dog.drooling_level.toFloat()

            binding.toleratesBeingAloneRatingBar.Parameters.text = parameters[5]
            binding.toleratesBeingAloneRatingBar.ratingBar.rating = viewModel.dog.tolerates_being_alone.toFloat()

            binding.barkingRatingBar.Parameters.text = parameters[6]
            binding.barkingRatingBar.ratingBar.rating = viewModel.dog.barking_level.toFloat()

            binding.apartmentLivingRatingBar.Parameters.text = parameters[7]
            binding.apartmentLivingRatingBar.ratingBar.rating = viewModel.dog.apartment_living.toFloat()

            binding.easyToTrainRatingBar.Parameters.text = parameters[8]
            binding.easyToTrainRatingBar.ratingBar.rating = viewModel.dog.easy_to_train.toFloat()

            binding.IntelligenceRatingBar.Parameters.text = parameters[9]
            binding.IntelligenceRatingBar.ratingBar.rating = viewModel.dog.intelligence.toFloat()

            binding.sheddingRatingBar.ratingBar.rating = viewModel.dog.shedding.toFloat()
            binding.otherPetsFriendlyRatingBar.ratingBar.rating = viewModel.dog.other_dogs_friendly.toFloat()
            binding.childrenFriendlyRatingBar.ratingBar.rating = viewModel.dog.kid_friendly.toFloat()

            // set the rating layout visible, the unique Parameters of the dog visible
            // and the unique Parameters of the cat invisible
            binding.infoPage.isVisible = true
            binding.dogsOnlyStats.isVisible = true
            binding.catsOnlyStats.isVisible = false
        }
        else if (activityViewModel.petType == "Cat" && viewModel.searchState) {

            binding.playfulnessRatingBar.Parameters.text = parameters[10]
            binding.groomingRatingBar.Parameters.text = parameters[11]

            // set the rating layout visible, the unique Parameters of the dog invisible
            // and the unique Parameters of the cat visible
            binding.infoPage.isVisible = true
            binding.dogsOnlyStats.isVisible = false
            binding.catsOnlyStats.isVisible = true
        }
        else {
            // set the rating layout not visible
            binding.infoPage.isVisible = false
        }

        // set the Parameters names that are belong to cats and dogs
        binding.sheddingRatingBar.Parameters.text = parameters[0]
        binding.otherPetsFriendlyRatingBar.Parameters.text = parameters[1]
        binding.childrenFriendlyRatingBar.Parameters.text = parameters[2]

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            // when the user pressed submit on the search
            override fun onQueryTextSubmit(breed: String): Boolean {
                binding.searchView.clearFocus()
                viewModel.getDog(breed)

                return false
            }

            // when the user are typing
            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }

        })

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe the dogs LiveData
        viewModel.dogs.observe(viewLifecycleOwner) {

            when (it.status) {
                is Success -> {
                    binding.infoPage.isVisible = true
                    binding.searchLoading.isVisible = false

                    viewModel.dog = it.status.data!!
                }
                is Error -> {
                    Toast.makeText(requireContext(),it.status.message, Toast.LENGTH_SHORT).show()
                    binding.searchLoading.isVisible = false
                    binding.infoPage.isVisible = false
                }
                is Loading -> {
                    binding.infoPage.isVisible = false
                    binding.searchLoading.isVisible = true
                }
                else -> {}
            }
        }
    }
}