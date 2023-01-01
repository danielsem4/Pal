package com.example.pal.ui.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.pal.R
import com.example.pal.data.repository.FirebaseImpl.AuthRepositoryFirebase
import com.example.pal.databinding.FragmentEntryBinding
import com.example.pal.databinding.FragmentLoginBinding
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared
import il.co.syntax.myapplication.util.Resource

class LoginFragment : Fragment() {

    private var binding : FragmentLoginBinding by autoCleared()
    private val viewModel:LoginViewModel by viewModels{
        LoginViewModel.LoginViewModelFactory(AuthRepositoryFirebase())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater, container, false)

        // user enter the application without an account
        binding.guestBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_entryFragment)
        }


        // users without account will press here and move to the sign up page
        binding.signupBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment4)
        }
        binding.loginBtn.setOnClickListener{

            viewModel.signInUser(binding.emailLogin.text.toString(),binding.passwordLogin.text.toString())
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userSignInStatus.observe(viewLifecycleOwner) {
            when (it) { // we need to add the progress bar
                is Resource.Success -> {
                    Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_loginFragment_to_entryFragment)
                }
                is Resource.Error ->{
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()

                }
            }
        }
        viewModel.currentUser.observe(viewLifecycleOwner){
            when (it) { // we need to add the progress bar
                is Resource.Success -> {//if the user is still login and didn't signout
                    Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
                    //findNavController().navigate(R.id.action_loginFragment_to_entryFragment)
                }


            }
        }


    }
}