package com.example.boostar_uaal.core.utils

import android.graphics.BlurMaskFilter
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Path
/**
 * Modificador personalizado para aplicar uno sombra de color.
 *
 * @param color El color principal del resplandor.
 * @param blurRadius La intensidad del desenfoque. Un valor mayor extiende más la luz. Si
 * se establece en 0.dp, el filtro se desactiva y dibuja una forma sólida.
 * @param cornerRadius El radio de las esquinas del componente. Es fundamental que este valor
 * coincida exactamente con el `clip` o `shape` del componente padre para que la sombra no
 * sobresalga por los bordes de forma extraña.
 * @param isTopOnly Si es `true`, aplica el radio de esquina exclusivamente en la parte superior
 * (TopLeft y TopRight). Este caso de uso está diseñado específicamente para tarjetas emergentes
 * desde abajo, como los modales o `BottomSheet`.
 */
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
            if (blurRadius != 0.dp) { frameworkPaint.maskFilter = BlurMaskFilter(
                    blurRadius.toPx(),
                    BlurMaskFilter.Blur.NORMAL
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
/**
 * Modificador personalizado que aplica un efecto visual de carga animado (Shimmer effect).
 *
 * DETALLES DE IMPLEMENTACIÓN:
 * - Ciclo de vida de la animación: Utiliza `rememberInfiniteTransition` y `infiniteRepeatable`
 * para ejecutar un bucle constante que se reinicia (`RepeatMode.Restart`) sin necesidad de
 * gestionar corrutinas ni estados manualmente.
 * - Rendimiento gráfico: Emplea `drawBehind` para pintar el gradiente directamente sobre el
 * lienzo (Canvas) subyacente del componente. Esta es la forma más optimizada de dibujar fondos
 * dinámicos en Compose.
 *
 * @param durationMillis Duración en milisegundos de un ciclo completo de la animación.
 * Por defecto es 1000ms (1 segundo). Disminuir este valor hará que el barrido de luz sea
 * más rápido y frenético.
 */
@Composable
fun Modifier.shimmer(
    durationMillis: Int = 1000,
): Modifier {
    val transition = rememberInfiniteTransition(label = "")

    val translateAnimation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 500f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                easing = LinearEasing,
            ),
            repeatMode = RepeatMode.Restart,
        ),
        label = "",
    )

    return drawBehind {
        drawRect(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color.LightGray.copy(alpha = 0.2f),
                    Color.LightGray.copy(alpha = 1.0f),
                    Color.LightGray.copy(alpha = 0.2f),
                ),
                start = Offset(x = translateAnimation, y = translateAnimation),
                end = Offset(x = translateAnimation + 100f, y = translateAnimation + 100f),
            )
        )
    }
}
