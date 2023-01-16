package com.example.pal.data.repository.Firebase

import androidx.lifecycle.MutableLiveData
import com.example.pal.data.models.Pet
import com.example.pal.data.repository.PetsRepository
import com.google.firebase.firestore.FirebaseFirestore
import il.co.syntax.myapplication.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import safeCall


class PetsRepositoryFirebase : PetsRepository {

    // the pets collection
    private val petsRef = FirebaseFirestore.getInstance().collection("pets")


    // get pets filtered by animal type
    override suspend fun getPets(animal: String) : Resource<List<Pet>> {

        // the calls wrapped with safe call to catch exceptions
        return safeCall {
            // filter and get only the dogs / cats
            val query = petsRef.whereEqualTo("animal", animal)
            val snapshot = query.get().await()

            val petsList = mutableListOf<Pet>()

            // iterate on the snapshot i got and convert it to Pet object and push it to the petsList
            for (doc in snapshot) {
                val pet = doc.toObject(Pet::class.java)
                petsList.add(pet)
            }
            Resource.Success(petsList)
        }

    }

    override suspend fun findDogByBreed(breed: String): Resource<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun getPet(id: String): Resource<Pet> {
        TODO("Not yet implemented")
    }

    override fun getPetsLiveData(data: MutableLiveData<Resource<List<Pet>>>) {

        data.postValue(Resource.Loading())

        petsRef.orderBy("animal").addSnapshotListener { snapshot, error ->

            if (error != null) {
                data.postValue(error.localizedMessage?.let { Resource.Error(it) })
            }
            if (snapshot != null && !snapshot.isEmpty) {
                data.postValue(Resource.Success(snapshot.toObjects(Pet::class.java)))
            }

            else {
                data.postValue(Resource.Error("No Data"))
            }
        }

    }

}