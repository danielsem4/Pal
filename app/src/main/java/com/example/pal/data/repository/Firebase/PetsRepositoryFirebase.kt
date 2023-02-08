package com.example.pal.data.repository.Firebase

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

    // the pets collection
    private var petsRef = FirebaseFirestore.getInstance().collection("pets")

    private val dogsRef = FirebaseFirestore.getInstance().collection("dogs")


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

    /*
        override suspend fun getPet(id: Int): Resource<Pet> {
            if (language == "iw")
                petsRef = FirebaseFirestore.getInstance().collection("petsHW")
            return safeCall {
                val pet =petsRef.document().id

                Resource.success(petG!!)
            }
        }
    */
    override suspend fun findDogByBreed(breed: String): Resource<Void> {
        TODO("Not yet implemented")
    }



    // get the dog info by breed
    override suspend fun getDogInfo(breed: String): Resource<Dog> {

        return safeCall {
            val query = dogsRef.whereEqualTo("Breed", breed)
            val dog = query.get().await().toObjects(Dog::class.java)

            Resource.success(dog[0])
        }
    }

    override fun getPetsLiveData(data: MutableLiveData<Resource<List<Pet>>>) {

        data.postValue(Resource.loading())

        petsRef.orderBy("animal").addSnapshotListener { snapshot, error ->

            if (error != null) {
                data.postValue(error.localizedMessage?.let { Resource.error(it) })
            }
            if (snapshot != null && !snapshot.isEmpty) {
                data.postValue(Resource.success(snapshot.toObjects(Pet::class.java)))
            }

            else {
                data.postValue(Resource.error("No Data"))
            }
        }

    }

}