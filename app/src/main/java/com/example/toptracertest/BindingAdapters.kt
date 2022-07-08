package com.example.toptracertest

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("goneUnless")
fun setGoneUnless(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("gifUrl")
fun setGif(view: ImageView, url: String) {
    if (url.isNotBlank()) Glide.with(view.context).load(url).asGif().into(view)
}