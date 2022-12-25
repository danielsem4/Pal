package com.example.pal.ui.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pal.R
import com.example.pal.databinding.FragmentEntryBinding
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared

class EntryFragment : Fragment() {

    private var binding : FragmentEntryBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEntryBinding.inflate(inflater, container, false)

        // on press moving to the cat section (Home screen with cats for adoption)
        binding.catSection.setOnClickListener{

        }

        // on press moving to the dog section (Home screen with dogs for adoption)
        binding.dogSection.setOnClickListener {

        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}