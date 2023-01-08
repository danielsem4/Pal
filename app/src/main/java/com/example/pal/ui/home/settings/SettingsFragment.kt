package com.example.pal.ui.home.settings

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pal.R
import com.example.pal.databinding.FragmentSettingsBinding
import com.example.pal.ui.HomeActivity
import com.google.android.material.bottomappbar.BottomAppBar.MenuAlignmentMode
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared

class SettingsFragment : Fragment() {

    private var binding : FragmentSettingsBinding by autoCleared()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        // the right arrow on the buttons
        val rightDrawable = getDrawable(requireContext(), R.drawable.ic_baseline_arrow_forward_ios_24)

        // the bottom menu ref, and set the manu to be visible every time we come back to this screen
        val navigationBar = (activity as HomeActivity).findViewById<ViewGroup>(R.id.bottom_navigation)
        navigationBar.isVisible = true

        // set the text for every btn in the settings
        binding.settingsTerms.settingsBtnText.text = "  Terms"
        binding.settingsContactUs.settingsBtnText.text = "  Contact Us"
        binding.settingsChangePassword.settingsBtnText.text = "  Change password"
        binding.settingsDeleteUser.settingsBtnText.text = "  Delete User"
        binding.settingsSignOut.settingsBtnText.text = "  Sign out"

        // set the drawable icons for every btn in the settings
        binding.settingsContactUs.settingsBtnText.setCompoundDrawablesWithIntrinsicBounds(
            getDrawable(requireContext(), R.drawable.ic_baseline_alternate_email_24),
            null, rightDrawable, null)

        binding.settingsTerms.settingsBtnText.setCompoundDrawablesWithIntrinsicBounds(
            getDrawable(requireContext(), R.drawable.ic_baseline_terms_file_24),
            null, rightDrawable, null)

        binding.settingsChangePassword.settingsBtnText.setCompoundDrawablesWithIntrinsicBounds(
            getDrawable(requireContext(), R.drawable.ic_baseline_lock_24),
            null, rightDrawable, null)

        binding.settingsDeleteUser.settingsBtnText.setCompoundDrawablesWithIntrinsicBounds(
            getDrawable(requireContext(), R.drawable.ic_baseline_delete_24),
            null, rightDrawable, null)

        binding.settingsSignOut.settingsBtnText.setCompoundDrawablesWithIntrinsicBounds(
            getDrawable(requireContext(), R.drawable.ic_baseline_logout_24),
            null, rightDrawable, null)

        // terms button
        binding.settingsTerms.settingsBtn.setOnClickListener {

            findNavController().navigate(R.id.action_settingsFragment_to_termsFragment)
            navigationBar.isVisible = false
        }

        // contact us button
        binding.settingsContactUs.settingsBtn.setOnClickListener {

            findNavController().navigate(R.id.action_settingsFragment_to_contactUsFragment)
            navigationBar.isVisible = false
        }

        // change password button
        binding.settingsChangePassword.settingsBtn.setOnClickListener {

            findNavController().navigate(R.id.action_settingsFragment_to_changePasswordFragment)
            navigationBar.isVisible = false
        }

        // delete user button
        binding.settingsDeleteUser.settingsBtn.setOnClickListener {

        }

        // sign out button
        binding.settingsSignOut.settingsBtn.setOnClickListener {

        }


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}