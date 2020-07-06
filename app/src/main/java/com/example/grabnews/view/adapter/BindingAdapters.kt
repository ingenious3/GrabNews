package com.example.grabnews.view.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.grabnews.R
import java.text.SimpleDateFormat
import java.util.*

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("previewImage")
    fun loadImage(imageView: ImageView, previewImage: String?) {
        if (!previewImage.isNullOrEmpty()) {
            Glide.with(imageView.context)
                .load(previewImage)
                .centerCrop()
                .into(imageView)
        } else {
            imageView.setImageResource(R.drawable.placeholder)
        }
    }

    @JvmStatic
    @BindingAdapter("dateText")
    fun setDateText(textView: TextView, text: String?) {
        text?.apply {
            val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).parse(text)
            if (date != null) {
                textView.text =
                    SimpleDateFormat("EEE, dd MMM yyyy HH:mm", Locale.getDefault()).format(date)
            }
        }
    }
}