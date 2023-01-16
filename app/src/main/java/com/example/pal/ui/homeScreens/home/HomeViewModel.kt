package com.example.pal.ui.homeScreens.home

import androidx.lifecycle.*
import com.example.pal.data.models.Pet
import com.example.pal.data.repository.PetsRepository
import com.example.pal.ui.signin.LoginViewModel
import il.co.syntax.myapplication.util.Resource
import kotlinx.coroutines.launch


class HomeViewModel(private val petsRep: PetsRepository) : ViewModel() {

    // the pets ref
    private val _pets : MutableLiveData<Resource<List<Pet>>> = MutableLiveData()

    // the pets ref we expose
    val pets : LiveData<Resource<List<Pet>>> = _pets


    // set the pets array with the get pets fun from the Pet repository
    fun getPets(animal: String) {
        viewModelScope.launch {
            _pets.value = Resource.Loading()
            _pets.value = petsRep.getPets(animal)
        }
    }


    @Suppress("UNCHECKED_CAST")
    class HomeViewModelFactory(private val petsRep: PetsRepository) : ViewModelProvider.NewInstanceFactory(){
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(petsRep) as T
        }
    }

}