package com.example.pal.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.pal.data.models.Pet
import il.co.syntax.myapplication.util.Resource

interface PetsRepository {

    // get dog info by breed (from dogs collection)
    suspend fun findDogByBreed(breed: String) : Resource<Void>

    // get all pets to the home screen by animal type(dpg / cat)
    suspend fun getPets(animal: String) : Resource<List<Pet>>

    // get dog info by id (on press of dog from the home screen)
    suspend fun getPet(id: String) : Resource<Pet>

    fun getPetsLiveData(data: MutableLiveData<Resource<List<Pet>>>)


}