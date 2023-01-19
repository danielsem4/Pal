package com.example.pal.ui.homeScreens.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.pal.R
import com.example.pal.databinding.FragmentFavoritesBinding
import com.example.pal.ui.MainActivityViewModel
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared

class FavoritesFragment : Fragment() {

    private var binding : FragmentFavoritesBinding by autoCleared()

    // the activity viewModel
    private val activityViewModel : MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFavoritesBinding.inflate(inflater, container, false)


        // navigate to the log in screen
        binding.signInFavoriteBtn.setOnClickListener {
            findNavController().navigate(R.id.action_favoritesFragment_to_loginFragment2)
        }

        println(activityViewModel.userStatus)

        binding.guestScreen.isVisible = !activityViewModel.userStatus
        binding.userScreen.isVisible = activityViewModel.userStatus

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}