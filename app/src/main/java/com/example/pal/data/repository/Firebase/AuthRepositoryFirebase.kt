package com.example.pal.data.repository.Firebase

import com.example.pal.data.models.User
import com.example.pal.data.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import il.co.syntax.myapplication.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import safeCall

class AuthRepositoryFirebase : AuthRepository {

    private val firebaseAuth = FirebaseAuth.getInstance() // the auth instance

    // users collection reference (in the Firestore)
    private val userRef = FirebaseFirestore.getInstance().collection("user")

    // check if you was logged in to the app and return him
    override suspend fun currentUser(): Resource<User> {

        // make async coroutine block to sync block inside the Dispatchers.IO scope
        return withContext(Dispatchers.IO){

            // the calls wrapped with safe call to catch exceptions
            safeCall {

                // get the current user who is signed in
                val user = userRef.document(firebaseAuth.currentUser!!.uid).get().await().toObject(User::class.java)

                Resource.Success(user!!)
            }
        }
    }

    override suspend fun login(email: String, password: String): Resource<User> {

        // make async coroutine block to sync block inside the Dispatchers.IO scope
        return withContext(Dispatchers.IO){

            // the calls wrapped with safe call to catch exceptions
            safeCall {

                // check if the email and password belongs to any user
                val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()

                // get the user that belongs to this email and password
                val user = userRef.document(result.user?.uid!!).get().await().toObject(User::class.java)!!

                Resource.Success(user)

            }
        }
    }

    // suspend fun because it can take some time
    override suspend fun createUser(
        userName: String,
        userEmail: String,
        userPhone: String,
        userLoginPass: String,
    )  : Resource<User>{

        // make async coroutine block to sync block inside the Dispatchers.IO scope
        return withContext(Dispatchers.IO){

            // the calls wrapped with safe call to catch exceptions
            safeCall {
                val registrationResult = firebaseAuth.createUserWithEmailAndPassword(
                    userEmail, userLoginPass).await()
                val userId = registrationResult.user?.uid!! // created user in the auth section
                val newUser = User(userName, userEmail, userPhone) // we create user for the firestore
                userRef.document(userId).set(newUser).await() // push the new user to the Users table

                Resource.Success(newUser)
            }

        }
    }


    override fun logout() {
        firebaseAuth.signOut()
    }
}

