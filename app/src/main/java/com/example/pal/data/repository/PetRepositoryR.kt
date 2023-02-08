package com.example.pal.data.repository

import android.app.Application
import com.example.pal.data.local_db.PetDao
import com.example.pal.data.local_db.PetDatabase
import com.example.pal.data.models.Pet
import com.example.pal.data.repository.Firebase.PetsRepositoryFirebase
import com.example.pal.util.performFetchingAndSaving
import javax.inject.Inject
import javax.inject.Singleton
import javax.sql.CommonDataSource

@Singleton
class PetRepositoryR @Inject constructor(
    private val remoteDataSource: PetsRepositoryFirebase,
    private val localDataSource:PetDao
) {

    fun getPets(animal:String)= performFetchingAndSaving(
        {localDataSource.getAllPets(animal)},
        {remoteDataSource.getPets(animal) },
        {localDataSource.insertPets(it)}
    )

    fun getPet(id:Int)= performFetchingAndSaving(
        {localDataSource.getPet(id)},
        {remoteDataSource.getPet(id) },
        {localDataSource.insertPet(it)}
    )






}


















/*
class PetRepositoryR(application: Application) {
    private var petDao:PetDao?
    init {
        val db = PetDatabase.getDatabase(application.applicationContext)
        petDao=db?.petDao() //initial petDao
    }

    fun getAllPets()=petDao?.getAllPets()

    suspend fun addPet(pet: Pet){
        petDao?.addPet(pet)
    }

    fun deletePet(pet: Pet){
        petDao?.deletePet(pet)
    }

    }
*/

