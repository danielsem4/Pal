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
import com.example.pal.databinding.FragmentSignupBinding
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared
import il.co.syntax.myapplication.util.Resource

class SignupFragment : Fragment() {

    private var binding : FragmentSignupBinding by autoCleared()
    private val viewModel:RegisterViewModel by viewModels(){
        RegisterViewModel.RegisterViewModelFactory(AuthRepositoryFirebase())
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSignupBinding.inflate(inflater, container, false)

        binding.signInBtn.setOnClickListener{
            viewModel.createUser(binding.nameSignIn.text.toString(),binding.emailSignIn.text.toString(),
            binding.phoneSignIn.text.toString(),binding.passwordSignIn.text.toString())
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.userRegistrationStatus.observe(viewLifecycleOwner){
            when(it){ // we need to add the progress bar
                is Resource.Success ->{
                    Toast.makeText(requireContext(),"Registration successful",Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_signupFragment_to_entryFragment2)
                }
                is Resource.Error ->{
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()

                }
            }
        }
    }
}