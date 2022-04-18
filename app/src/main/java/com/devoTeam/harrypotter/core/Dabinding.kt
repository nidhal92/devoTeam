package com.devoTeam.harrypotter.core

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:loadUrl")
fun setImage(view: ImageView, url: String) {
    ImageHelper().loadUrl(url,view)
}
@BindingAdapter("android:textDescription")
fun setDescription(view: TextView, textList: List<String>) {
    var text =""
    textList.forEach {
        text += it
    }
    view.text=text
}
