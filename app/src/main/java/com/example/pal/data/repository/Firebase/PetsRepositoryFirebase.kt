package com.example.pal.data.repository.Firebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pal.data.models.Dog
import com.example.pal.data.models.Pet
import com.example.pal.data.repository.PetsRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.example.pal.util.Resource
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await
import safeCall
import java.util.*
import javax.inject.Inject


class PetsRepositoryFirebase @Inject constructor() : PetsRepository {

    // the system language
    private val language = Locale.getDefault().language.toString()

    // the pets collection reference, the pets for adoption
    private var petsRef = FirebaseFirestore.getInstance().collection("pets")

    // the dog collection reference, the dogs info by breed
    private val dogsRef = FirebaseFirestore.getInstance().collection("dogs")

    // users collection reference (in the Firestore)
    private val userRef = FirebaseFirestore.getInstance().collection("user")


    // get pets filtered by animal type
    override suspend fun getPets(animal: String) : Resource<List<Pet>> {

        if (language == "iw")
            petsRef = FirebaseFirestore.getInstance().collection("petsHW")

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
            Resource.success(petsList)
        }

    }

    override suspend fun getPet(id: Int): Resource<Pet> {

        if (language == "iw")
            petsRef = FirebaseFirestore.getInstance().collection("petsHW")


        return safeCall {
            // Retrieve the document with the given id
            val document = petsRef.document(id.toString()).get().await()

            // Convert the document to a Pet object
            val pet = document.toObject(Pet::class.java)

            Resource.success(pet!!)
        }
    }

    override suspend fun findDogByBreed(breed: String): Resource<Dog> {

        return safeCall {
            // Retrieve the document with the given breed
            val query = dogsRef.whereEqualTo("Breed", breed)
            val document = query.get().await()

            // Convert the document to Dog type object
            val dog = document.documents[0].toObject(Dog::class.java)

            Resource.success(dog!!)
        }
    }

    // get the dog info by breed
    override suspend fun getDogs(): Resource<List<Dog>> {

        return safeCall {
            val snapshot = dogsRef.get().await()
            val dogsList = mutableListOf<Dog>()

            // iterate on the snapshot i got and convert it to Pet object and push it to the petsList
            for (doc in snapshot) {
                val dog = doc.toObject(Dog::class.java)
                dogsList.add(dog)
            }
            Resource.success(dogsList)

        }
    }

    override suspend fun getFavorites(): Resource<List<Pet>> {
        TODO()
    }

}