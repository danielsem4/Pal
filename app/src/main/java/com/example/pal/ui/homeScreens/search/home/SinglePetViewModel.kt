package com.example.pal.ui.homeScreens.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pal.data.repository.PetRepositoryR
import com.example.pal.data.repository.PetsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SinglePetViewModel @Inject constructor(petRepositoryR: PetRepositoryR) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    // function that will store the pets we pressed
    val pet = Transformations.switchMap(_id) {
        petRepositoryR.getPet(it)
    }

    fun setId(id: Int) {
        _id.value = id
    }


}