package com.esports.bazar.view.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.esports.bazar.R
import com.esports.bazar.core.DataState
import com.esports.bazar.databinding.FragmentRegisterBinding
import com.esports.bazar.isEmpty


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    val viewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        emailFocusListener()
        passwordFocusListener()

        binding.btnRegister.setOnClickListener {
            setListener()
        }

        registrationObserver()


        return binding.root
    }

    private fun registrationObserver() {
        viewModel.registrationResponse.observe(viewLifecycleOwner){
when(it){
    is DataState.Error -> Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
    is DataState.Loading -> Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
    is DataState.Success -> {
        Toast.makeText(context, "${it.Data}", Toast.LENGTH_LONG).show()
        //Toast.makeText(context, "Registration Successfully", Toast.LENGTH_SHORT).show()
       // findNavController().navigate(R.id.action_registerFragment_to_dashboardFragment)
    }


}
        }
    }

    private fun setListener() {
        with(binding) {
            etName.isEmpty()
            etEmail.isEmpty()
            etPassword.isEmpty()

            if (!etName.isEmpty() && !etEmail.isEmpty() && !etPassword.isEmpty()) {
                submitform()
            }
        }

    }

    private fun submitform() {
        val validName = binding.nameContainer.helperText == null
        val validEmail = binding.emailContainer.helperText == null
        val validPassword = binding.passwordContainer.helperText == null

        if (validName && validEmail && validPassword) {
            registerUser()
        } else {
            invalidForm()
        }
    }

    private fun invalidForm() {
        Toast.makeText(context, "Invalid Input", Toast.LENGTH_SHORT).show()
    }

    private fun registerUser() {


        val user = User(

            binding.etName.text.toString(),
            binding.etEmail.text.toString(),
            binding.etPassword.text.toString(),
            "Seller",
            ""
        )

        viewModel.userRegistration(user)

    }

    private fun passwordFocusListener() {
        binding.etPassword.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.passwordContainer.helperText = validPassword()
            }

        }
    }

    private fun validPassword(): String? {
        val validPassword = binding.etPassword.text.toString()
        if (validPassword.length < 8) {
            return "Minimum 8 character required"
        }
        return null

    }

    private fun emailFocusListener() {
        binding.etEmail.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.emailContainer.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {
        val emailText = binding.etEmail.text.toString()
        val pattern = "^[a-z0-9+_.-]+@[a-z.-]{4,7}\\.[a-z]{2,5}$"
        if (!emailText.matches(pattern.toRegex())) {
            return "Invalid email address"
        }
        return null

    }

}