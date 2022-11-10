package ru.coralcode.arty.ui.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.scale
import kotlinx.coroutines.*
import ru.coralcode.arty.R

private const val VIEW_NOT_MEASURED_MSG = "Tried to access property before onMeasure()"
private const val DEFAULT_STROKE_WIDTH = 20f
private const val START_COORDINATE = 0f
private const val DRAW_DELAY = 5L
private const val PATH_DRAWN_DELAY = 100L
private const val DRAW_ACTION_REPEAT_COUNT = 24

class LoadingPicView(context: Context, attributeSet: AttributeSet): View(context, attributeSet) {

    private val drawable = ContextCompat.getDrawable(context, R.drawable.loading)

    private var colors = listOf(resources.getColor(R.color.main_pink, null), Color.BLACK)

    private var animationJob: Job? = null

    private var isLoading = false

    private val paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        isAntiAlias = true
        strokeWidth = DEFAULT_STROKE_WIDTH
    }

    private val rectPaint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        isAntiAlias = true
        strokeWidth = DEFAULT_STROKE_WIDTH
    }

    private var path = Path()

    private val rect: RectF by lazy {
        assertMeasured()
        return@lazy RectF(width.toFloat(), 0f, 0f, height.toFloat())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawRect(rect, rectPaint)
        canvas?.drawBitmap(prepareDrawable(width, height), -25f,-25f, paint)
        canvas?.drawPath(path, paint)
    }

    private fun prepareDrawable(width: Int, height: Int) =
        drawable!!.toBitmap(width, height).scale(width + 50, height + 50)

    fun startLoading(scope: CoroutineScope) {
        isLoading = true
        animationJob = scope.launch { drawSequence() }
    }

    fun stopLoading() { animationJob?.cancel(); isLoading = false }

    private suspend fun drawSequence() {
        while (isLoading) {
            for (color in colors) {
                rectPaint.color = paint.color
                paint.color = color
                path.moveTo(width.toFloat() / 2, START_COORDINATE)
                drawRectangle()
            }
        }
    }

    private suspend fun drawRectangle() {

        val widthStep = width.toFloat() / DRAW_ACTION_REPEAT_COUNT
        val heightStep = height.toFloat() / DRAW_ACTION_REPEAT_COUNT

        drawHalfSide { path.rLineTo(widthStep, START_COORDINATE) }
        drawSide { path.rLineTo(START_COORDINATE, heightStep) }
        drawSide { path.rLineTo(-widthStep, START_COORDINATE) }
        drawSide { path.rLineTo(START_COORDINATE, -heightStep) }
        drawHalfSide { path.rLineTo(widthStep, START_COORDINATE) }

        delay(PATH_DRAWN_DELAY)
        path.reset()
    }

    private suspend fun drawSide(whatToDraw: () -> Unit) {
        repeat(DRAW_ACTION_REPEAT_COUNT) {
            delay(DRAW_DELAY)
            whatToDraw.invoke()
            invalidate()
        }
    }

    private suspend fun drawHalfSide(whatToDraw: () -> Unit) {
        repeat(DRAW_ACTION_REPEAT_COUNT / 2) {
            delay(DRAW_DELAY)
            whatToDraw.invoke()
            invalidate()
        }
    }

    private fun assertMeasured() =
        require(width != 0 && height != 0) { VIEW_NOT_MEASURED_MSG }

    fun setColors(value: List<Int>) {
        colors = value
    }
}