package com.example.toptracertest.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toptracertest.network.data.NetworkResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    val usernameText = MutableStateFlow("")
    val passwordText = MutableStateFlow("")

    sealed class Command {
        object LoginSuccessful : Command()
        data class Error(val message: String) : Command()
    }

    private val _command = MutableSharedFlow<Command>()
    val command: SharedFlow<Command> = _command

    fun performLogin() {
        viewModelScope.launch {
            val loginResponse =
                repository.login(username = usernameText.value, password = passwordText.value)
            when(loginResponse) {
                is NetworkResponse.GeneralError -> _command.emit(Command.Error(loginResponse.errorMessage))
                is NetworkResponse.Success -> _command.emit(Command.LoginSuccessful)
            }
        }
    }
}