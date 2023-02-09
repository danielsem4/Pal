package com.example.pal.ui.homeScreens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.pal.R
import com.example.pal.data.models.Pet
import com.example.pal.databinding.FragmentSinglePetBinding
import com.example.pal.ui.MainActivity
import com.example.pal.ui.MainActivityViewModel
import com.example.pal.util.Loading
import com.example.pal.util.Success
import dagger.hilt.android.AndroidEntryPoint
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared

@AndroidEntryPoint
class SinglePet : Fragment() {

    private var binding: FragmentSinglePetBinding by autoCleared()

    private val viewModel: SinglePetViewModel by viewModels()

    // the activity viewModel
    private val activityViewModel : MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentSinglePetBinding.inflate(inflater, container, false)

        binding.guest.isVisible = !activityViewModel.userStatus
        binding.favorites.isVisible = activityViewModel.userStatus

        // the bottom menu ref, and set the manu to be visible every time we come back to this screen
        val navigationBar = (activity as MainActivity).findViewById<ViewGroup>(R.id.bottom_navigation)
        navigationBar.isVisible = false



        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.pet.observe(viewLifecycleOwner) {
            when (it.status) {
                //we need to add here instead the progress bar
                is Loading -> Toast.makeText(requireContext(), "loading", Toast.LENGTH_SHORT).show()

                is Success -> {
                    updatePet(it.status.data!!)//send to this function the pet to update the detail
                }
                is Error -> {
                    Toast.makeText(requireContext(), it.status.message, Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
        }
        // getting the id of the pet after we pressed the recycler view item
        arguments?.getInt("petId")?.let {

            //set the id in the view model
            viewModel.setId(it)
        }
    }

    // update the pet information, set the necessary fields in the fragment
    private fun updatePet(pet: Pet) {

        binding.name.text = pet.name
        binding.sex.text = pet.sex
        binding.age.text = pet.id.toString()
        binding.description.text = pet.description
        binding.breed.text = pet.breed
        Glide.with(requireContext()).load(pet.pic).circleCrop().into(binding.image)

    }
}