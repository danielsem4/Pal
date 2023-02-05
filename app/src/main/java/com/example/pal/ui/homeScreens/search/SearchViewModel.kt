package com.example.pal.ui.homeScreens.search

import androidx.lifecycle.ViewModel
import com.example.pal.data.repository.PetsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {

    var searchState: Boolean = false

    // set the user status if he is logged in or guest
    @JvmName("setUserStatus1")
    fun setUserStatus(status: Boolean) {
        searchState = status
    }

}