package com.example.toptracertest.gif

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.toptracertest.R
import com.example.toptracertest.databinding.FragmentGifBinding
import com.example.toptracertest.subscribeToLifecycle
import org.koin.androidx.viewmodel.ext.android.viewModel

class GifFragment : Fragment() {

    private var _binding: FragmentGifBinding? = null
    private val binding get() = _binding!!


    private val viewModel: GifViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGifBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = viewLifecycleOwner
            it.viewModel = viewModel
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLifecycle(viewModel.command) {
            when (it) {
                GifViewModel.Command.Logout -> findNavController().navigate(R.id.action_login_to_gif)
                is GifViewModel.Command.LoadGif -> loadGif(it.url)
            }
        }
    }

    private fun loadGif(url: String) {
        binding.let {
            Glide.with(activity).load(url).asGif().into(binding.gif)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}