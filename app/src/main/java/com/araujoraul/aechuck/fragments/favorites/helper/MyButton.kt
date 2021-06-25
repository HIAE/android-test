package com.araujoraul.aechuck.fragments.favorites.helper

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

interface ButtonHelperClickListener{
    fun onButtonClick(pos: Int)
}

class MyButton(
    private val context: Context,
    private val text: String,
    private val textSize: Int,
    private val imageResId: Int,
    private val color: Int,
    private val listener: ButtonHelperClickListener
) {
    private var pos: Int = 0
    private var clickRegion: RectF? = null
    private val resources: Resources = context.resources

    fun onClick(x: Float, y: Float): Boolean{

        if (clickRegion != null && clickRegion!!.contains(x,y)){
            listener.onButtonClick(pos)
            return true
        }
        return false
    }

    fun onDraw(c: Canvas, rectF: RectF, pos: Int){

        val p = Paint()
        p.color = color
        c.drawRect(rectF, p)

        //Text
        p.color = Color.WHITE
        p.textSize = textSize.toFloat()

        val r = Rect()
        val cHeight = rectF.height()
        val cWidth = rectF.width()
        p.textAlign = Paint.Align.CENTER
        p.getTextBounds(text, 0, text.length, r)
        var x: Float
        var y: Float

        if (imageResId == 0){
            x = cWidth / 2f - r.width() / 2f - r.left
            y = cHeight / 2f + r.height() / 2f - r.bottom
            c.drawText(text, rectF.left + x, rectF.top + y, p)

        } else {
            val drawable = ContextCompat.getDrawable(context, imageResId)
            val bitmap = drawableToBitmap(drawable)
            c.drawBitmap(bitmap, (rectF.left+rectF.right)/2, (rectF.top+rectF.bottom)/2, p)
        }
        clickRegion = rectF
        this.pos = pos
    }

    private fun drawableToBitmap(drawable: Drawable?): Bitmap {
        if (drawable is BitmapDrawable) return drawable.bitmap

        val bitmap = Bitmap.createBitmap(drawable!!.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

}