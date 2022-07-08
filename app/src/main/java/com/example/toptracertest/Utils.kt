package com.example.toptracertest

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

fun <T> Fragment.subscribeToLifecycle(
    flow: SharedFlow<T>,
    action: suspend (value: T) -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect(action)
        }
    }
}