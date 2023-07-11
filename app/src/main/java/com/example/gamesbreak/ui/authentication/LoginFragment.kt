package com.example.gamesbreak.ui.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.gamesbreak.R
import com.example.gamesbreak.activities.HomeActivity
import com.example.gamesbreak.data.UserPreferences
import com.example.gamesbreak.databinding.FragmentLoginBinding
import com.example.gamesbreak.utils.startNewActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val storage = UserPreferences(requireContext())

        val btnLogin = binding.btnLogIn

        btnLogin.setOnClickListener {
            val username = binding.etUserName.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val user = viewModel.login(username, password)
            if (user != null) {
                coroutineScope.launch {
                    viewModel.saveUserCredentials(user,storage)
                    val params = arrayOf(
                        Pair("NAME_USER", user.name),
                        Pair("ID_USER", user.id.toString())
                    )
                    requireActivity().startNewActivity(HomeActivity::class.java, params)
                }
            } else {
                Toast.makeText(requireContext(), getString(R.string.menssage_error_login), Toast.LENGTH_LONG).show()
            }
        }
    }

}
