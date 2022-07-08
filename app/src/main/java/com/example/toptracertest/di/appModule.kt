package com.example.toptracertest.di

import com.example.toptracertest.gif.GifRepository
import com.example.toptracertest.gif.GifViewModel
import com.example.toptracertest.login.LoginRepository
import com.example.toptracertest.login.LoginViewModel
import com.example.toptracertest.network.GiphyApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { LoginRepository() }
    single { GifRepository(api = get()) }
    single { GiphyApi.instance }
    viewModel { LoginViewModel(repository = get()) }
    viewModel { GifViewModel(repository = get()) }
}