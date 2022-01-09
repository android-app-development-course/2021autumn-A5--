package com.example.fanlaisu

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.TypedValue
import android.util.AttributeSet
import android.view.View

/**
 * Created by WangChunLei on 2016.1.16
 * e-mail:wcl_android@163.com
 */
class GradientProgressBar : View {
    /*圆弧线宽*/
    private val circleBorderWidth =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20f, resources.displayMetrics)

    /*内边距*/
    private val circlePadding =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20f, resources.displayMetrics)

    /*字体大小*/
    private val textSize =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 45f, resources.displayMetrics)

    /*绘制圆周的画笔*/
    private var backCirclePaint: Paint? = null

    /*绘制圆周白色分割线的画笔*/
    private var linePaint: Paint? = null

    /*绘制文字的画笔*/
    private var textPaint: Paint? = null

    /*百分比*/
    private var percent = 0

    /*渐变圆周颜色数组*/
    private val gradientColorArray = intArrayOf(
//        Color.GREEN,
        Color.parseColor("#8DB6CD"),
        Color.parseColor("#E0EEE0"),
//        Color.GREEN
    )
    private var gradientCirclePaint: Paint? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        backCirclePaint = Paint()
        backCirclePaint!!.style = Paint.Style.STROKE
        backCirclePaint!!.isAntiAlias = true
        backCirclePaint!!.color = Color.LTGRAY
        backCirclePaint!!.strokeWidth = circleBorderWidth
        //        backCirclePaint.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.OUTER));
        gradientCirclePaint = Paint()
        gradientCirclePaint!!.style = Paint.Style.STROKE
        gradientCirclePaint!!.isAntiAlias = true
        gradientCirclePaint!!.color = Color.LTGRAY
        gradientCirclePaint!!.strokeWidth = circleBorderWidth
        linePaint = Paint()
        linePaint!!.color = Color.WHITE
        linePaint!!.strokeWidth = 5f
        textPaint = Paint()
        textPaint!!.isAntiAlias = true
        textPaint!!.textSize = textSize
        textPaint!!.color = Color.BLACK
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val measureWidth = MeasureSpec.getSize(widthMeasureSpec)
        val measureHeight = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(
            Math.min(measureWidth, measureHeight),
            Math.min(measureWidth, measureHeight)
        )
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //1.绘制灰色背景圆环
        canvas.drawArc(
            RectF(
                circlePadding * 2, circlePadding * 2,
                measuredWidth - circlePadding * 2, measuredHeight - circlePadding * 2
            ), -90f, 360f, false, backCirclePaint!!
        )
        //2.绘制颜色渐变圆环
        val linearGradient = LinearGradient(
            circlePadding, circlePadding,
            measuredWidth - circlePadding,
            measuredHeight - circlePadding,
            gradientColorArray, null, Shader.TileMode.MIRROR
        )
        gradientCirclePaint!!.shader = linearGradient
        gradientCirclePaint!!.setShadowLayer(10f, 10f, 10f, Color.RED)
        canvas.drawArc(
            RectF(
                circlePadding * 2, circlePadding * 2,
                measuredWidth - circlePadding * 2, measuredHeight - circlePadding * 2
            ), -90f, (percent / 100.0).toFloat() * 360, false, gradientCirclePaint!!
        )

        //半径
        val radius = (measuredWidth - circlePadding * 3) / 2
        //X轴中点坐标
        val centerX = measuredWidth / 2

        //3.绘制100份线段，切分空心圆弧
        var i = 0f
        while (i < 360) {
            val rad = i * Math.PI / 180
            val startX = (centerX + (radius - circleBorderWidth) * Math.sin(rad)).toFloat()
            val startY = (centerX + (radius - circleBorderWidth) * Math.cos(rad)).toFloat()
            val stopX = (centerX + radius * Math.sin(rad) + 1).toFloat()
            val stopY = (centerX + radius * Math.cos(rad) + 1).toFloat()
            canvas.drawLine(startX, startY, stopX, stopY, linePaint!!)
            i += 7.2f
        }

        //4.绘制文字
        val textWidth = textPaint!!.measureText("$percent")
        val textHeight =
            (Math.ceil((textPaint!!.fontMetrics.descent - textPaint!!.fontMetrics.ascent).toDouble()) + 2).toInt()
        canvas.drawText(
            "$percent",
            centerX - textWidth / 2,
            (centerX + textHeight / 4).toFloat(),
            textPaint!!
        )
    }

    /**
     * 设置百分比
     *
     * @param percent
     */
    fun setPercent(percent: Int) {
        var percent = percent
        if (percent < 0) {
            percent = 0
        } else if (percent > 100) {
            percent = 100
        }
        this.percent = percent
        invalidate()
    }
}