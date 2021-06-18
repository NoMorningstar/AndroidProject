package com.itri.snake

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.concurrent.fixedRateTimer

class GameView(context: Context, attrs: AttributeSet) : View(context, attrs){
    //苹果和蛇身的位置，预设是null
    var apple: Position? = null
    var snakeBody: List<Position>? = null
    var size = 0
    //初始长度
    private val gap = 3
    //蛇跟苹果的颜色
    private var paint = Paint().apply {
        this.color = Color.BLACK
    }
    private var paintApple = Paint().apply {
        this.color = Color.RED
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.run {

            apple?.run {
                drawRect(x*size.toFloat() + gap, y*size.toFloat() + gap,
                        (x+1)*size.toFloat() + gap, (y+1)*size.toFloat() + gap, paintApple)
            }
            snakeBody?.forEach {
                drawRect(
                    ((it.x)*size).toFloat() + gap, ((it.y)*size).toFloat() + gap,
                    ((it.x+1)*size).toFloat() - gap, ((it.y+1)*size).toFloat() - gap, paint)
            }
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        size  = width / 20
    }
}