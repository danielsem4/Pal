package com.example.pal.ui.signin

import android.util.Patterns
import androidx.lifecycle.*
import com.example.pal.data.models.User
import com.example.pal.data.repository.AuthRepository
import il.co.syntax.myapplication.util.Resource
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class RegisterViewModel(private val repository:AuthRepository):ViewModel() {

    private val _userRegistrationStatus = MutableLiveData<Resource<User>>()

    val userRegistrationStatus:LiveData<Resource<User>> = _userRegistrationStatus

    fun createUser(userName: String,userEmail: String,userPhone: String,userPass: String){
        val error = if(userEmail.isNotEmpty() || userPass.isNotEmpty() || userName.isNotEmpty())
            "empty String"
        else if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches())
            "Not a valid Email"
        else null

        error?.let {
            _userRegistrationStatus.postValue(Resource.Error(it))// postValue and not value for the back thread
        }
        _userRegistrationStatus.postValue(Resource.Loading())
        viewModelScope.launch {
            val registrationResult = repository.createUser(userName,userEmail,userPhone,userPass)
            _userRegistrationStatus.postValue(registrationResult)
        }
    }

    class RegisterViewModelFactory(private val repo:AuthRepository): ViewModelProvider.NewInstanceFactory(){
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RegisterViewModel(repo) as T
        }
    }

}