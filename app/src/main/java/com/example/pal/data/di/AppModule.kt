package com.example.pal.data.di

import android.content.Context
import com.example.pal.data.local_db.PetDatabase
import com.example.pal.data.repository.AuthRepository
import com.example.pal.data.repository.Firebase.AuthRepositoryFirebase
import com.example.pal.data.repository.Firebase.PetsRepositoryFirebase
import com.example.pal.data.repository.PetRepositoryR
import com.example.pal.data.repository.PetsRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    // firebase providers
    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    // auth repo provider
    @Provides
    fun provideAuthRepositoryFirebase(firebaseAuth: FirebaseAuth):
            AuthRepository = AuthRepositoryFirebase(firebaseAuth)

    // pet repo provider
    @Provides

    fun providePetsRepositoryFirebase(): PetsRepository =
        PetsRepositoryFirebase(firebaseAuth = FirebaseAuth.getInstance())


    //local db
    @Provides
    @Singleton
    fun provideLocalDataBase(@ApplicationContext appContext: Context): PetDatabase =
        PetDatabase.getDatabase(appContext)//return the pet database

    @Provides
    @Singleton
    fun providePetDao(database: PetDatabase) = database.petDao()


}