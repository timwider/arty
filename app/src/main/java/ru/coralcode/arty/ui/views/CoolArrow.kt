package ru.coralcode.arty.ui.views

import android.content.*
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.Configuration
import android.content.res.Resources
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.*
import android.util.AttributeSet
import android.view.Display
import android.view.View
import androidx.annotation.UiContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.coralcode.arty.R
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream

private const val BUNDLE_POSITION_KEY = "currentPosition"

class CoolArrow(context: Context, attributeSet: AttributeSet): View(context, attributeSet) {

    init {
        isSaveEnabled = true
    }

    private var currentPosition = Position.UP

    private var job: Job? = null

    private var isAnimating = false

    private val paint = Paint().apply {
        color = resources.getColor(R.color.main_pink, null)
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeWidth = 8f
        this.strokeCap = Paint.Cap.ROUND
    }

    fun onClick(scope: CoroutineScope, callback: (Position) -> Unit) {
        job = scope.launch {
            if (currentPosition == Position.UP) {
                animateDown(callback)
            } else animateUp(callback)
        }
    }

    private var currentY = 20f

    private suspend fun animateDown(callback: (Position) -> Unit) {
        isAnimating = true
        val endY = height - 20f
        val step = (height - 40f) / 25

        currentY = 20f

        while(currentY < endY) {
            currentY += step
            delay(5)
            invalidate()
        }
        currentPosition = Position.DOWN
        job?.cancel()
        job = null
        isAnimating = false
        callback.invoke(currentPosition)
    }

    private suspend fun animateUp(callback: (Position) -> Unit) {
        isAnimating = true
        val step = (height - 40f) / 25
        val startY = 20f

        currentY = height - 20f

        while (currentY > startY) {
            currentY -= step
            delay(5)
            invalidate()
        }
        currentPosition = Position.UP
        job?.cancel()
        job = null
        isAnimating = false
        callback.invoke(currentPosition)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (!isAnimating) {
            currentY = if (currentPosition == Position.DOWN) height - 20f else 20f
        }
        canvas?.drawLine(20f, height.toFloat() / 2, width.toFloat() / 2, currentY, paint)
        canvas?.drawLine(width - 20f, height / 2f, width / 2f, currentY, paint)
    }


    class SavedState: BaseSavedState {
        constructor(superState: Parcelable): super(superState)
        constructor(parcel: Parcel): super(parcel)

    }

    enum class Position { UP, DOWN }
}