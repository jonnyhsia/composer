package com.jonnyhsia.uilib.widget

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper

/**
 * Bounce Effect Over Scroll RecyclerView
 */
class BounceRecyclerView : RecyclerView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    override fun setLayoutManager(layout: RecyclerView.LayoutManager) {
        super.setLayoutManager(layout)
        if (isInEditMode) {
            return
        }
        if (layout is LinearLayoutManager) {
            OverScrollDecoratorHelper.setUpOverScroll(this, if (layout.orientation == LinearLayoutManager.HORIZONTAL) {
                OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL
            } else {
                OverScrollDecoratorHelper.ORIENTATION_VERTICAL
            })
        }
    }

    private val INVALID_POINTER = -1

    private var mScrollPointerId = INVALID_POINTER
    private var mInitialTouchX: Int = 0
    private var mInitialTouchY: Int = 0

    private var mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop

    override fun setScrollingTouchSlop(slopConstant: Int) {
        super.setScrollingTouchSlop(slopConstant)
        val vc = ViewConfiguration.get(context)
        when (slopConstant) {
            RecyclerView.TOUCH_SLOP_DEFAULT -> mTouchSlop = vc.scaledTouchSlop
            RecyclerView.TOUCH_SLOP_PAGING -> mTouchSlop = vc.scaledPagingTouchSlop
            else -> {
            }
        }
    }

    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        e ?: return super.onInterceptTouchEvent(e)

        val action = e.actionMasked
        val actionIndex = e.actionIndex

        when (action) {
            MotionEvent.ACTION_DOWN -> {
                mScrollPointerId = e.getPointerId(0)
                mInitialTouchX = (e.x + 0.5f).toInt()
                mInitialTouchY = (e.y + 0.5f).toInt()
                return super.onInterceptTouchEvent(e)
            }

            MotionEvent.ACTION_POINTER_DOWN -> {
                mScrollPointerId = e.getPointerId(actionIndex)
                mInitialTouchX = (e.getX(actionIndex) + 0.5f).toInt()
                mInitialTouchY = (e.getY(actionIndex) + 0.5f).toInt()
                return super.onInterceptTouchEvent(e)
            }

            MotionEvent.ACTION_MOVE -> {
                val index = e.findPointerIndex(mScrollPointerId)
                if (index < 0) {
                    return false
                }

                val x = (e.getX(actionIndex) + 0.5f).toInt()
                val y = (e.getY(actionIndex) + 0.5f).toInt()
                if (scrollState != RecyclerView.SCROLL_STATE_DRAGGING) {
                    val dx = x - mInitialTouchX
                    val dy = y - mInitialTouchY
                    val canScrollHorizontally = layoutManager.canScrollHorizontally()
                    val canScrollVertically = layoutManager.canScrollVertically()
                    var startScroll = false
                    if (canScrollHorizontally && Math.abs(dx) > mTouchSlop && (Math.abs(dx) >= Math.abs(dy) || canScrollVertically)) {
                        startScroll = true
                    }
                    if (canScrollVertically && Math.abs(dy) > mTouchSlop && (Math.abs(dy) >= Math.abs(dx) || canScrollHorizontally)) {
                        startScroll = true
                    }
                    return startScroll && super.onInterceptTouchEvent(e)
                }
                return super.onInterceptTouchEvent(e)
            }
            else -> return super.onInterceptTouchEvent(e)
        }
    }
}
