package com.example.toptracertest.uifeatures.gif

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toptracertest.appdata.Gif
import com.example.toptracertest.network.data.NetworkResponse
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class GifViewModel(private val repository: GifRepository) : ViewModel() {

    private val _author = MutableStateFlow("")
    val author: StateFlow<String> = _author
    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title
    private val _url = MutableStateFlow("")
    val url: StateFlow<String> = _url

    enum class State {
        LOADING, LOADED
    }

    private val viewState = MutableStateFlow(State.LOADING)
    val isSuccessState: StateFlow<Boolean> = viewState.map { it == State.LOADED }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)
    val isLoadingState = viewState.map { it == State.LOADING }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)

    sealed class Command {
        object Logout : Command()
    }

    private val _command = MutableSharedFlow<Command>()
    val command: SharedFlow<Command> = _command

    init {
        viewModelScope.launch {
            val networkResponse = repository.fetchTrendyGif()
            when (networkResponse) {
                is NetworkResponse.GeneralError -> onError(networkResponse.errorMessage)
                is NetworkResponse.Success -> onSuccess(networkResponse.data)
            }
        }
    }

    private suspend fun onError(errorMessage: String) {
        viewState.emit(State.LOADED)
        _title.emit(errorMessage)
    }

    private suspend fun onSuccess(gif: Gif) {
        viewState.emit(State.LOADED)
        _author.emit(gif.author)
        _title.emit(gif.title)
        _url.emit(gif.url)
    }
    fun onLogoutClicked() {
        viewModelScope.launch {
            _command.emit(Command.Logout)
        }
    }
}