package com.example.pal.ui

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    var petType : String = ""

    var userStatus : Boolean = false

    // set the animal type
    fun setAnimalType(type: String) {
        petType = type
    }

    // set the user status if he is logged in or guest
    @JvmName("setUserStatus1")
    fun setUserStatus(status: Boolean) {
        userStatus = status
    }

}