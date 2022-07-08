package com.example.toptracertest.login

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.toptracertest.R
import com.example.toptracertest.databinding.FragmentLoginBinding
import com.example.toptracertest.subscribeToLifecycle
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        FragmentLoginBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = viewLifecycleOwner
            it.viewModel = viewModel
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLifecycle(viewModel.command) {
            when (it) {
                is LoginViewModel.Command.Error -> showError(it.message)
                LoginViewModel.Command.LoginSuccessful -> navigateToGif()
            }
        }
    }

    private fun showError(message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle("An Error has occurred")
            .setMessage(message)
            .show()
    }

    private fun navigateToGif() {
        findNavController().navigate(R.id.action_login_to_gif)
    }
}