package com.example.pal.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pal.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    // the user status
    var userStatus: Boolean = false

    // the pet type Dog/Cat
    var petType : String = ""

    // set the animal type
    fun setAnimalType(type: String) {
        petType = type
    }

    // check with the repository about the user status
    init {
        viewModelScope.launch {
            userStatus = repository.checkUserStatus()
        }
    }

    // set the user status if he is logged in or guest
    @JvmName("setUserStatus1")
    fun setUserStatus(status: Boolean) {
        userStatus = status
    }

}