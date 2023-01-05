package com.example.pal.ui.home.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pal.databinding.FragmentSettingsBinding
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared

class SettingsFragment : Fragment() {

    private var binding : FragmentSettingsBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        // the sign out btn
        binding.signOutBtn.setOnClickListener {

        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}