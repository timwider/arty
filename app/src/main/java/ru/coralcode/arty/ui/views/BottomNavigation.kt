package ru.coralcode.arty.ui.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.coralcode.arty.R

class BottomNavigation(
    context: Context,
    attributeSet: AttributeSet): BottomNavigationView(context, attributeSet) {

        var paint = Paint().apply {
            this.color = Color.WHITE
            this.isAntiAlias = true
            this.strokeWidth = 20f
        }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawLine(0f, height.toFloat(), width.toFloat(), height.toFloat(), paint)
    }
}