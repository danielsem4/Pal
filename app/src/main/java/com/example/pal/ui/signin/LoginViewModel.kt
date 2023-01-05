package com.example.pal.ui.signin

import androidx.lifecycle.*
import com.example.pal.data.models.User
import com.example.pal.data.repository.AuthRepository
import il.co.syntax.myapplication.util.Resource
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class LoginViewModel(private val repository: AuthRepository): ViewModel() {

    private val _userSignInStatus = MutableLiveData<Resource<User>>() // follow after the register state
    val userSignInStatus: LiveData<Resource<User>> = _userSignInStatus

    private val _currentUser = MutableLiveData<Resource<User>>()// the current user if someone is logged in
    val currentUser: LiveData<Resource<User>> = _currentUser


    init {
        viewModelScope.launch {
            _currentUser.postValue(Resource.Loading())
            _currentUser.postValue(repository.currentUser()) // run it on back thread
        }
    }

    fun signInUser(userEmail: String, userPass: String){
        if(userEmail.isEmpty() || userPass.isEmpty())
            _userSignInStatus.postValue(Resource.Error("Empty email or password\n please try again"))

        else{
            _userSignInStatus.postValue(Resource.Loading())
            viewModelScope.launch {
                val loginResult = repository.login(userEmail,userPass)
                _userSignInStatus.postValue(loginResult)
            }
        }


    }


    class LoginViewModelFactory(private val repo:AuthRepository): ViewModelProvider.NewInstanceFactory(){
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LoginViewModel(repo) as T
        }
    }


}