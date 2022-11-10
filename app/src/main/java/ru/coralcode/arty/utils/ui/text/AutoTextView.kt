package ru.coralcode.arty.utils.ui.text

import android.widget.TextView

private const val DEFAULT_WRITING_INTERVAL = 200L
private const val DEFAULT_CLEARING_INTERVAL = 150L
private const val REQUIRE_POSITIVE_VALUE_MESSAGE = "Value must be a positive integer."
private const val REQUIRE_LIST_NOT_EMPTY = "List of texts must not be empty."


abstract class AutoTextView(
    private val textView: TextView
) {

    protected var textsToWrite: List<String> = emptyList()

    protected var writingInterval: Long = DEFAULT_WRITING_INTERVAL

    protected var clearingInterval: Long = DEFAULT_CLEARING_INTERVAL

    abstract fun launch()

    abstract fun stop()

    abstract fun updateText()

    protected var something: Int = 0

    @JvmName("setWritingIntervalMethod")
    fun setWritingInterval(millis: Long) {
        require(millis > 0) { REQUIRE_POSITIVE_VALUE_MESSAGE }
        writingInterval = millis
    }

    @JvmName("setClearingIntervalMethod")
     fun setClearingInterval(millis: Long) {
         require(millis > 0) { REQUIRE_POSITIVE_VALUE_MESSAGE }
        clearingInterval = millis
    }

    @JvmName("setTextsToWriteMethod")
    fun setTextsToWrite(list: List<String>) {
        require(list.isNotEmpty()) { REQUIRE_LIST_NOT_EMPTY }
        textsToWrite = list
    }
}