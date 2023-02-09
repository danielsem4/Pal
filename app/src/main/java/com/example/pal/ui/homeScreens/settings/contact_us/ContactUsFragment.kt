package com.example.pal.ui.homeScreens.settings.contact_us

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pal.databinding.FragmentContactUsBinding
import dagger.hilt.android.AndroidEntryPoint
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared

@AndroidEntryPoint
class ContactUsFragment : Fragment() {
    private var binding : FragmentContactUsBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentContactUsBinding.inflate(inflater, container, false)

        return binding.root

    }

}