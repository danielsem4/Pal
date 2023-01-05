package com.example.pal.ui.signin

import android.util.Patterns
import androidx.lifecycle.*
import com.example.pal.data.models.User
import com.example.pal.data.repository.AuthRepository
import il.co.syntax.myapplication.util.Resource
import kotlinx.coroutines.launch

class SignupViewModel(private val repository: AuthRepository) : ViewModel() {

    // the user status
    private val _userRegistrationStatus = MutableLiveData<Resource<User>>()

    // listener to the user status
    val userRegistrationStatus: LiveData<Resource<User>> = _userRegistrationStatus

    fun createUser(userName: String, userEmail: String, userPhone: String, userPass: String){

        // check that the fields are not empty
        val error = if(userEmail.isNotEmpty() || userPass.isNotEmpty() || userName.isNotEmpty() || userPhone.isEmpty())
            "empty String"

        // check if the user email is valid
        else if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches())
            "Not a valid Email"

        else null

        error?.let {
            // postValue was used for the option to make it on back thread
            _userRegistrationStatus.postValue(Resource.Error(it))
        }

        // approach to firebase
        _userRegistrationStatus.postValue(Resource.Loading())

        // the viewModel scope is on the main coroutine,
        // but the coroutine that called inside will move it to back coroutine
        viewModelScope.launch {
            val registrationResult = repository.createUser(userName, userEmail, userPhone, userPass)
            _userRegistrationStatus.postValue(registrationResult)
        }
    }


    // factory method
    @Suppress("UNCHECKED_CAST")
    class RegisterViewModelFactory(private val repo: AuthRepository) : ViewModelProvider.NewInstanceFactory(){

        override fun <T : ViewModel> create(modelClass: Class<T>) : T {
            return SignupViewModel(repo) as T
        }
    }

}