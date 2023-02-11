package com.example.pal.ui.homeScreens.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.pal.R
import com.example.pal.data.models.Cat
import com.example.pal.data.models.Dog
import com.example.pal.data.models.Pet
import com.example.pal.databinding.FragmentSinglePetInfoBinding
import com.example.pal.ui.MainActivity
import com.example.pal.ui.MainActivityViewModel

import dagger.hilt.android.AndroidEntryPoint
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared

@AndroidEntryPoint
class SinglePetInfo : Fragment() {

    private var binding: FragmentSinglePetInfoBinding by autoCleared()

    // the activity viewModel
    private val activityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentSinglePetInfoBinding.inflate(inflater, container, false)
        activityViewModel

        // the bottom menu ref, and set the manu to be visible every time we come back to this screen
        val navigationBar =
            (activity as MainActivity).findViewById<ViewGroup>(R.id.bottom_navigation)
        navigationBar.isVisible = false

        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // the dog rating category name
        val parameters = this.resources.getStringArray(R.array.parameters)

        if (activityViewModel.petType == "Cat") {

            val cat: Cat = arguments?.getSerializable("cat") as Cat
        } else {
            val dog: Dog = arguments?.getSerializable("dog") as Dog
            updateDog(dog, parameters)
        }
    }

    // update the dog breed info
    private fun updateDog(dog: Dog, parameters: Array<String>) {

        binding.breedDescribe.text = dog.Breed
        binding.weightDescribe.text = dog.Weight
        binding.lifeSpanDescribe.text = dog.Life_Span

        binding.sheddingRatingBar.Parameters.text = parameters[0]
        binding.sheddingRatingBar.ratingBar.rating = dog.Shedding.toFloat()

        binding.otherPetsFriendlyRatingBar.Parameters.text = parameters[1]
        binding.otherPetsFriendlyRatingBar.ratingBar.rating = dog.Other_Dogs_Friendly.toFloat()

        binding.childrenFriendlyRatingBar.Parameters.text = parameters[2]
        binding.childrenFriendlyRatingBar.ratingBar.rating = dog.Kid_Friendly.toFloat()

        binding.sizeRatingBar.Parameters.text = parameters[3]
        binding.sizeRatingBar.ratingBar.rating = dog.Size.toFloat()

        binding.droolingRatingBar.Parameters.text = parameters[4]
        binding.droolingRatingBar.ratingBar.rating = dog.Drooling_Level.toFloat()

        binding.toleratesBeingAloneRatingBar.Parameters.text = parameters[5]
        binding.toleratesBeingAloneRatingBar.ratingBar.rating = dog.Tolerates_Being_Alone.toFloat()

        binding.barkingRatingBar.Parameters.text = parameters[6]
        binding.barkingRatingBar.ratingBar.rating = dog.Barking_Level.toFloat()

        binding.apartmentLivingRatingBar.Parameters.text = parameters[7]
        binding.apartmentLivingRatingBar.ratingBar.rating = dog.Apartment_Living.toFloat()

        binding.easyToTrainRatingBar.Parameters.text = parameters[8]
        binding.easyToTrainRatingBar.ratingBar.rating = dog.Easy_To_Train.toFloat()

        binding.IntelligenceRatingBar.Parameters.text = parameters[9]
        binding.IntelligenceRatingBar.ratingBar.rating = dog.Intelligence.toFloat()

        Glide.with(requireContext()).load(dog.image).circleCrop().into(binding.petImage)
    }

    // update the dog breed info
    private fun updateCat(cat: Cat) {


    }

}