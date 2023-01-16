package com.example.pal.ui.entry


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pal.R
import com.example.pal.databinding.FragmentEntryBinding
import com.example.pal.ui.MainActivity
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared


class EntryFragment : Fragment() {

    private var binding : FragmentEntryBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEntryBinding.inflate(inflater, container, false)

        // the bundle i will sent to the next fragments
        val bundle = Bundle()

        // the bottom menu ref, and set the manu to be invisible every time we come back to this screen
        val navigationBar = (activity as MainActivity).findViewById<ViewGroup>(R.id.bottom_navigation)
        navigationBar.isVisible = false

        // on press moving to the dog section (Home screen with dogs for adoption)
        binding.dogSection.setOnClickListener {

            //set the animal type to dog
            bundle.putString("animal", "Dog")
            findNavController().navigate(R.id.action_entryFragment_to_homeFragment, bundle)
        }

        // on press moving to the cat section (Home screen with cats for adoption)
        binding.catSection.setOnClickListener{

            //set the animal type to cat
            bundle.putString("animal", "Cat")
            findNavController().navigate(R.id.action_entryFragment_to_homeFragment, bundle)
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}