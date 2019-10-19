package com.sun.myapplication

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.annotation.IdRes
import com.bumptech.glide.Glide

object LoadImage {
    fun loadImage(image: ImageView, link: String) {
        Glide.with(image.context)
            .load(link)
            .fallback(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(image)
    }

    @SuppressLint("ResourceType")
    fun loadImageFromDrawable(image: ImageView, @IdRes resourceId: Int?) {
        Glide.with(image.context)
            .load(resourceId)
            .fallback(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(image)

    }
}