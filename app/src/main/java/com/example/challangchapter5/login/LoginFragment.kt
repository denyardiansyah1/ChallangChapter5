package com.example.challangchapter5.login

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.challangchapter5.R
import com.example.challangchapter5.databinding.FragmentLoginBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class LoginFragment : Fragment() {

    lateinit var binding : FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.belumPunyaAkun.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        viewModel.user.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isEmpty()) {
                binding.etEmail.error = "email required"
                binding.etEmail.requestFocus()

            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.etPassword.error = "invalid email"
                binding.etPassword.requestFocus()

            }
            if (password.isEmpty()) {
                binding.etPassword.error = "password is required"
                binding.etPassword.requestFocus()

            }
            if (!binding.alertEmail.isErrorEnabled && !binding.tilPassword.isErrorEnabled) {
                viewModel.loginFirebase(email, password)

            }
        }
        viewModel.login.observe(viewLifecycleOwner) {
            if (it.equals("Login Success!", true)) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            } else {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

    }

}