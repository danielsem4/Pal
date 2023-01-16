package com.example.pal.ui.homeScreens.home

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.pal.R
import com.example.pal.data.repository.Firebase.PetsRepositoryFirebase
import com.example.pal.databinding.FragmentHomeBinding
import com.example.pal.ui.MainActivity
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared
import il.co.syntax.myapplication.util.Resource

class HomeFragment : Fragment() {

    private var binding : FragmentHomeBinding by autoCleared()

    private val viewModel : HomeViewModel by viewModels {
        HomeViewModel.HomeViewModelFactory(PetsRepositoryFirebase())
    }

    private var imageList = ArrayList<SlideModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // set the 5 slider images and there titles
        imageList.add(SlideModel(R.drawable.home_slider1, "Our Angels"))
        imageList.add(SlideModel(R.drawable.home_slider2, "Our Angels"))
        imageList.add(SlideModel(R.drawable.home_slider3, "Our Angels"))
        imageList.add(SlideModel(R.drawable.home_slider4, "Our Angels"))
        imageList.add(SlideModel(R.drawable.home_slider5, "Our Angels"))

        // fill the home slider with the images
        binding.imageHomeSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)

        // the bottom menu ref, and set the manu to be visible every time we come back to this screen
        val navigationBar = (activity as MainActivity).findViewById<ViewGroup>(R.id.bottom_navigation)
        navigationBar.isVisible = true

        viewModel.getPets(arguments?.getString("animal").toString())

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeRecycler.layoutManager = LinearLayoutManager(requireContext())

        binding.homeRecycler.adapter = HomeAdapter(object :  HomeAdapter.PetsListener {
            override fun onPetClicked(index: Int) {
                TODO("Not yet implemented")
            }
        })

        viewModel.pets.observe(viewLifecycleOwner) {

            when (it) {

                // when the user status is loading we will show the loading anim ui
                is Resource.Loading -> {
                    binding.homeUi.isVisible = false
                    binding.homeLoading.isVisible = true
                }

                // when the user status is success we will move to the next page and reset the ui
                is Resource.Success -> {
                    binding.homeUi.isVisible = true
                    binding.homeLoading.isVisible = false
                    (binding.homeRecycler.adapter as HomeAdapter).setPets(it.data!!)
                }

                // if the user status is failed we will pop up the message and wont change the ui
                is Resource.Error ->{
                    binding.homeUi.isVisible = true
                    binding.homeLoading.isVisible = false
                    Toast.makeText(requireContext(),it.message, Toast.LENGTH_SHORT).show()
                }
            }

        }


    }

}