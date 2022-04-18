package com.devoTeam.harrypotter.core

import android.widget.ImageView
import com.bumptech.glide.Glide


class ImageHelper : IImageProcess {

    override fun loadUrl(url: String, imageView: ImageView) {
        Glide.with(imageView.context).load(url ?: "").into(imageView)

    }
}
