package com.example.appmovie

import android.view.View
import com.example.appmovie.databinding.HeaderComponentBinding

class HeaderComponent(private val binding: HeaderComponentBinding) {

    fun setHeaderText(text: String) {
        binding.headerTextView.text = text
    }

    fun setTextColor(color: Int) {
        binding.headerTextView.setTextColor(color)
    }

    fun setOnClickListener(listener: View.OnClickListener) {
        binding.headerTextView.setOnClickListener(listener)
    }

}
