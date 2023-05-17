package com.softwareit.geographicatlas.utils

import android.graphics.Color
import android.os.Build
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.annotation.RequiresApi
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

@RequiresApi(Build.VERSION_CODES.N)
fun getColoredText(text: String, fromClass: String): SpannableString {
    val decodedText = Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT).toString()
    val spannableText = SpannableStringBuilder(decodedText)
    val grayColorSpan = ForegroundColorSpan(Color.GRAY)
    val grayTextLastCharIndex = decodedText.indexOf(":")

    if (grayTextLastCharIndex != -1) {
        spannableText.setSpan(
            grayColorSpan,
            0,
            grayTextLastCharIndex + 1,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        if (fromClass == "CountryDetails") {
            val lineBreakIndex = grayTextLastCharIndex + 1
            if (lineBreakIndex < spannableText.length) {
                spannableText.insert(lineBreakIndex, "\n    ")
            }
        }

    }

    return SpannableString.valueOf(spannableText)
}

