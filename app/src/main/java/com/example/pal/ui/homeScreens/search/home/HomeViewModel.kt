package com.example.pal.ui.homeScreens.search.home

import androidx.lifecycle.*
import com.example.pal.data.models.Pet
import com.example.pal.data.repository.PetRepositoryR
import com.example.pal.data.repository.PetsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.pal.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val petsRep: PetsRepository,private val petRepositoryR: PetRepositoryR) : ViewModel() {

    // the pets ref
    private val _pets : MutableLiveData<Resource<List<Pet>>> = MutableLiveData()

    // the pets ref we expose
    val pets : LiveData<Resource<List<Pet>>> = _pets


    lateinit var petBen: LiveData<Resource<List<Pet>>>

    // set the pets array with the get pets fun from the Pet repository
    fun getPets(animal: String) {

        petBen=petRepositoryR.getPets(animal)

    }

}