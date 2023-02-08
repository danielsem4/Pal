package com.example.pal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.pal.data.repository.PetRepositoryR
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SinglePetViewModel @Inject constructor(petRepositoryR: PetRepositoryR):ViewModel() {


    private val _id = MutableLiveData<Int>()

    val pet =Transformations.switchMap(_id){
        petRepositoryR.getPet(it)
    }

    fun setId(id:Int){
        _id.value=id
    }







}