package com.example.pal.ui.homeScreens.search

import androidx.lifecycle.*
import com.example.pal.data.models.Dog
import com.example.pal.data.repository.PetsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.pal.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val dogsRep: PetsRepository) : ViewModel() {

    // the dogs ref
    private val _dogs : MutableLiveData<Resource<List<Dog>>> = MutableLiveData()

    // the dogs ref we expose
    val dogs : LiveData<Resource<List<Dog>>> = _dogs

    // set the dogs array with the get dogs fun from the Pet repository
    fun getDogs() {
        viewModelScope.launch {

            _dogs.value = Resource.loading()
            _dogs.value = dogsRep.getDogs()

        }
    }
}