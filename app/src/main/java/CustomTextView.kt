package com.example.appmovie

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.withStyledAttributes

class CustomTextView {

    class CustomTextView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
    ) : AppCompatTextView(context, attrs, defStyleAttr) {

        private var customText: String = "SSSSSS"

        init {
            context.withStyledAttributes(attrs, R.styleable.CustomTextView) {
                customText = getString(R.styleable.CustomTextView_customText) ?: "ddddddddd"
            }
            text = customText
        }

        fun setCustomText(text: String) {
            customText = text
            setText(text)
        }
    }
}

