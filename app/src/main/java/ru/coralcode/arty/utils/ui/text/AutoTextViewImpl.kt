package ru.coralcode.arty.utils.ui.text

import android.annotation.SuppressLint
import android.widget.TextView
import kotlinx.coroutines.delay

class AutoTextViewImpl(private val textView: TextView): AutoTextView(textView) {

    override fun launch() {

        updateText()

    }

    override fun stop() {
        textView.removeCallbacks(null)
    }

    @SuppressLint("SetTextI18n")
    override fun updateText() {
        for (word in textsToWrite) {
            for (letter in word) {
                textView.postDelayed( {textView.text = textView.text.toString() + letter }, 1000L)
            }
        }
    }

}