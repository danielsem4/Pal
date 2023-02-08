package com.example.pal.data.di

import com.example.pal.data.repository.AuthRepository
import com.example.pal.data.repository.Firebase.AuthRepositoryFirebase
import com.example.pal.data.repository.Firebase.PetsRepositoryFirebase
import com.example.pal.data.repository.PetsRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
        PetsRepositoryFirebase()
}