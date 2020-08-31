package com.anwesh.uiprojects.ishooterview

/**
 * Created by anweshmishra on 01/09/20.
 */

import android.view.View
import android.view.MotionEvent
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.RectF
import android.content.Context
import android.app.Activity

val colors : Array<Int> = arrayOf(
        "#F44336",
        "#673AB7",
        "#4CAF50",
        "#FF9800",
        "#03A9F4"
).map({Color.parseColor(it)}).toTypedArray()
val parts : Int = 3
val scGap : Float = 0.02f / parts
val barWFactor : Float = 6.5f
val barHFactor : Float = 2.9f
val delay : Long = 20
val backColor : Int = Color.parseColor("#BDBDBD")

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawIShooter(scale : Float, w : Float, h : Float, paint : Paint) {
    val barW : Float = Math.min(w, h) / barWFactor
    val barH : Float = Math.min(w, h) / barHFactor
    val r : Float = barW / 2
    val sf : Float = scale.sinify()
    val sf1 : Float = sf.divideScale(0, parts)
    val sf2 : Float = sf.divideScale(1, parts)
    val sf3 : Float = sf.divideScale(2, parts)
    val offset = -barH * 1.5f
    val y : Float = offset + (-h + r - offset) * sf3
    save()
    translate(w / 2, h)
    drawRect(RectF(-barW / 2, -barH * sf1, barW / 2, 0f), paint)
    drawCircle(0f, y, r * sf2, paint)
    restore()
}

fun Canvas.drawISNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    paint.color = colors[i]
    drawIShooter(scale, w, h, paint)
}

class IShooterView(ctx : Context) : View(ctx) {

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
}