package com.example.pal.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pal.data.remote_db.Firebase.AuthRepository
import com.example.pal.data.remote_db.Firebase.PetsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: AuthRepository, private val petsRepository: PetsRepository) :
    ViewModel() {

    // the user status
    var userStatus: Boolean = false

    var userFavorites: List<String> = emptyList()

    // the pet type Dog/Cat
    var petType: String = ""

    // set the animal type
    fun setAnimalType(type: String) {
        petType = type
    }

    fun updateFavorites(id: String) {
        viewModelScope.launch {
            userFavorites = petsRepository.addToFavorites(id)
        }
    }

    fun removePetFromFavorites(id: String) {
        viewModelScope.launch {
            userFavorites = petsRepository.removePetFromFavorites(id)
        }
    }

    // check with the repository about the user status
    init {
        viewModelScope.launch {
            userStatus = repository.checkUserStatus()
            if (userStatus) {
                userFavorites = repository.getUserFavorites()
            }
        }
    }

    // set the user status if he is logged in or guest
    @JvmName("setUserStatus1")
    fun setUserStatus(status: Boolean) {
        userStatus = status
    }

}