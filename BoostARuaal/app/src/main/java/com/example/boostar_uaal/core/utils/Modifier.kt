package com.example.boostar_uaal.core.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Path

fun Modifier.glow(
    color: Color,
    blurRadius: Dp = 20.dp,
    cornerRadius: Dp = 0.dp,
    isTopOnly: Boolean = false
): Modifier = this.drawBehind {
    drawIntoCanvas { canvas ->
        val paint = Paint().apply {
            val frameworkPaint = asFrameworkPaint()
            frameworkPaint.color = color.toArgb()
            if (blurRadius != 0.dp) { frameworkPaint.maskFilter = android.graphics.BlurMaskFilter(
                    blurRadius.toPx(),
                    android.graphics.BlurMaskFilter.Blur.NORMAL
                )
            }
        }
        if (cornerRadius > 0.dp) {
            if (isTopOnly) {
                val path = Path().apply {
                    addRoundRect(
                        RoundRect(
                            rect = Rect(0f, 0f, size.width, size.height),
                            topLeft = CornerRadius(cornerRadius.toPx(), cornerRadius.toPx()),
                            topRight = CornerRadius(cornerRadius.toPx(), cornerRadius.toPx())
                        )
                    )
                }
                canvas.drawPath(path, paint)
            } else {
                canvas.drawRoundRect(
                    left = 0f,
                    top = 0f,
                    right = size.width,
                    bottom = size.height,
                    radiusX = cornerRadius.toPx(),
                    radiusY = cornerRadius.toPx(),
                    paint = paint
                )
            }
        } else {
            canvas.drawRect(
                left = 0f,
                top = 0f,
                right = size.width,
                bottom = size.height,
                paint = paint
            )
        }
    }
}
