package com.example.pal.data.local_db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pal.data.models.Pet

@Dao
interface PetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)//if we add pet that already exist , replace it to the new one
    suspend fun insertPets(pet:List<Pet>)

    @Delete
    fun deletePet(pet: Pet)

    //@Query("SELECT * FROM pets_table ORDER BY content ASC")//give me all the item by content in order ascending
    //fun getPets(): LiveData<List<Pet>>

    @Query("SELECT * FROM pets_table WHERE animal =:animal")
    fun getAllPets(animal: String): LiveData<List<Pet>>

    @Query("SELECT * FROM pets_table WHERE animal = :animal")
    fun getAllDogs(animal:String): LiveData<List<Pet>>

    @Query("SELECT * FROM pets_table WHERE animal = :animal")
    fun getAllCats(animal: String): LiveData<List<Pet>>
}