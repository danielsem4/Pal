package com.example.pal.data.repository

import il.co.syntax.myapplication.util.Resource

interface TaskRepository {

    // all the data base calls for

    suspend fun findDogByBreed(breed: String) : Resource<Void>
    suspend fun getPets(
        age: String, breed: String, description: String, name: String, pic: String, animal: String)


    suspend fun addToFavorite()
    suspend fun removeFromFavorite()

    suspend fun getPetById(id: String)




}