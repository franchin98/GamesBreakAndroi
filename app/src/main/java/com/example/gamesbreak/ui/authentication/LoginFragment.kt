package com.example.gamesbreak.ui.authentication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.gamesbreak.R
import com.example.gamesbreak.activities.HomeActivity
import com.example.gamesbreak.data.User
import com.example.gamesbreak.databinding.FragmentLoginBinding
import com.example.gamesbreak.services.LoginService

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        val btnLogin = binding.btnLogIn

        btnLogin.setOnClickListener {
            runLogIn()
        }

        return binding.root
    }

    private fun runLogIn() {
        val user = LoginService.validateLogin(
            binding.etUserName.text.toString(),
            binding.etPassword.text.toString()
        )

        if (user is User) {
            val intentHomeActivity = Intent(requireContext(), HomeActivity::class.java)
            intentHomeActivity.putExtra("NAME_USER", user.name)
            intentHomeActivity.putExtra("ID_USER", user.id)
            startActivity(intentHomeActivity)
        } else {
            Toast.makeText(requireContext(), getString(R.string.menssage_error_login), Toast.LENGTH_LONG).show()
        }
    }
}
