package com.example.pal.ui.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pal.R
import com.example.pal.databinding.FragmentEntryBinding
import com.example.pal.databinding.FragmentLoginBinding
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared

class LoginFragment : Fragment() {

    private var binding : FragmentLoginBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater, container, false)

        // users without account will press here and move to the sign up page
        binding.signupBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment4)
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}