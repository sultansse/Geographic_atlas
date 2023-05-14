package com.softwareit.geographicatlas.utils

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

@BindingAdapter("android:imgUrl")
fun loadImgUrl(view: ShapeableImageView, url: String?) {
    url?.let {
        Glide.with(view)
            .load(url)
            .into(view)
    }
}
