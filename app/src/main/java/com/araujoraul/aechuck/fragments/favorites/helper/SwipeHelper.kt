package com.araujoraul.aechuck.fragments.favorites.helper

import android.content.Context
import android.graphics.*
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

abstract class SwipeHelper(
    context: Context,
    private val recyclerView: RecyclerView,
    private var buttonWidth: Int
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    private var buttonList: MutableList<MyButton>? = null
    lateinit var gestureDetector: GestureDetector
    var swipePosition = -1
    var swipeThreshold = 0.5f
    val buttonBuffer: MutableMap<Int, MutableList<MyButton>>

    lateinit var removerQueue: Queue<Int>


    abstract fun instantiateMyButton(
        viewHolder: RecyclerView.ViewHolder,
        buffer: MutableList<MyButton>
    )

    private val gestureListener = object : GestureDetector.SimpleOnGestureListener() {

        override fun onSingleTapUp(e: MotionEvent?): Boolean {

            for (button in buttonList!!) {
                if (button.onClick(e!!.x, e.y)) break
            }

            return true
        }

    }

    private val onTouchListener = object : View.OnTouchListener {

        override fun onTouch(v: View?, event: MotionEvent): Boolean {

            if (swipePosition < 0) return false
            val point = Point(event.rawX.toInt(), event.rawY.toInt())
            val swipeViewHolder = recyclerView.findViewHolderForAdapterPosition(swipePosition)
            val swipedItem: View = swipeViewHolder!!.itemView
            val rect = Rect()
            swipedItem.getGlobalVisibleRect(rect)

            if (event.action == MotionEvent.ACTION_DOWN ||
                event.action == MotionEvent.ACTION_MOVE ||
                event.action == MotionEvent.ACTION_UP
            ) {

                if (rect.top < point.y && rect.bottom > point.y) {
                    gestureDetector.onTouchEvent(event)

                } else {
                    removerQueue.add(swipePosition)
                    swipePosition = -1
                    recoverSwipeItem()
                }
            }
            return false
        }

    }

    private fun recoverSwipeItem() {
        while (!removerQueue.isEmpty()) {
            val pos: Int = removerQueue.poll()!!

            if (pos > -1) recyclerView.adapter!!.notifyItemChanged(pos)
        }
    }

    private fun attachSwipe() {
        val itemTouchHelper = ItemTouchHelper(this)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }


    class IntLinkedList : LinkedList<Int>() {
        override fun contains(element: Int): Boolean = false

        override fun lastIndexOf(element: Int): Int = element

        override fun remove(element: Int): Boolean = false

        override fun indexOf(element: Int): Int = element

        override fun add(element: Int): Boolean =
            if (contains(element)) false else super.add(element)

    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val pos = viewHolder.adapterPosition

        if (swipePosition != pos)
            removerQueue.add(swipePosition)
        swipePosition = pos

        if (buttonBuffer.containsKey(swipePosition))
            buttonList = buttonBuffer[swipePosition]
        else
            buttonList!!.clear()
        buttonBuffer.clear()
        swipeThreshold = 0.5f * buttonList!!.size * buttonWidth
        recoverSwipeItem()
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float = swipeThreshold

    override fun getSwipeEscapeVelocity(defaultValue: Float): Float = 0.1f * defaultValue

    override fun getSwipeVelocityThreshold(defaultValue: Float): Float = 5.0f * defaultValue

    override fun onChildDraw(
        c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
        dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean
    ) {

        val pos = viewHolder.adapterPosition
        var translationX = dX
        val itemView: View = viewHolder.itemView

        if (pos < 0) {
            swipePosition = pos
            return
        }

        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            if (dX < 0) {
                var buffer: MutableList<MyButton> = ArrayList()

                if (!buttonBuffer.containsKey(pos)) {
                    instantiateMyButton(viewHolder, buffer)
                    buttonBuffer[pos] = buffer

                } else {
                    buffer = buttonBuffer[pos]!!
                }
                translationX = dX * buffer.size * buttonWidth / itemView.width
                drawButton(c, itemView, buffer, pos, translationX)
            }
        }
        super.onChildDraw(
            c,
            recyclerView,
            viewHolder,
            translationX,
            dY,
            actionState,
            isCurrentlyActive
        )
    }

    private fun drawButton(
        c: Canvas,
        itemView: View,
        buffer: MutableList<MyButton>,
        pos: Int,
        translationX: Float
    ) {
        val buttonWidthWithoutPadding = buttonWidth - 20.toFloat()
        val corners = 16f
        val p = Paint()
        p.color = Color.parseColor("#ff0000")
        val rigthtButton = RectF(
            itemView.right - buttonWidthWithoutPadding,
            itemView.top.toFloat(),
            itemView.right.toFloat(),
            itemView.bottom.toFloat()
        )

        for (button in buffer) {
            button.onDraw(c, rigthtButton, pos)
            c.drawRoundRect(
                itemView.right - buttonWidthWithoutPadding,
                itemView.top.toFloat(),
                itemView.right.toFloat(),
                itemView.bottom.toFloat(),
                corners,
                corners,
                p
            )
            drawText("Excluir", c, rigthtButton, p)
        }
    }

    private fun drawText(text: String, c: Canvas, button: RectF, p: Paint) {
        val textSize = 40f
        p.color = Color.WHITE
        p.isAntiAlias = true
        p.textSize = textSize
        val textWidth: Float = p.measureText(text)
        c.drawText(text, button.centerX() - textWidth / 2, button.centerY() + textSize / 2, p)
    }


    init {
        this.buttonList = ArrayList()
        this.gestureDetector = GestureDetector(context, gestureListener)
        this.recyclerView.setOnTouchListener(onTouchListener)
        this.buttonBuffer = HashMap()
        this.removerQueue =
            IntLinkedList()

        attachSwipe()
    }


}