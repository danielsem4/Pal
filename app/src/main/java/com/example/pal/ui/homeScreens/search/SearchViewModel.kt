package com.example.pal.ui.homeScreens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pal.data.models.Dog
import com.example.pal.data.repository.PetsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.pal.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(private val dogsRep: PetsRepository) : ViewModel() {

    var searchState: Boolean = false

    // the pets ref
    private val _dogs : MutableLiveData<Resource<Dog>> = MutableLiveData()

    // the pets ref we expose
    val dogs : LiveData<Resource<Dog>> = _dogs

    // the dog info we will get from the firebase
    lateinit var dog : Dog

    // set the pets array with the get pets fun from the Pet repository
    fun getDog(breed: String) {
        viewModelScope.launch {
            _dogs.value = Resource.loading()
            _dogs.value = dogsRep.getDogInfo(breed)
        }
    }

    // set the user status if he is logged in or guest
    @JvmName("setUserStatus1")
    fun setUserStatus(status: Boolean) {
        searchState = status
    }

}