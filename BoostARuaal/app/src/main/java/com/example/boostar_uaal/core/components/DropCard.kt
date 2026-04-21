package com.example.boostar_uaal.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.core.entities.Drop
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit
/**
 * TO-DO / REFACTOR PENDIENTE: Componente programado para una reescritura completa desde cero.
 *
 * Este componente (`DropCard`) y sus funciones auxiliares (`CountdownTimer`, etc.) cumplen su
 * propósito visual inmediato, pero acumulan deuda técnica estructural. El desarrollador que
 * retome esta tarea deberá rehacerlo aplicando mejores prácticas de arquitectura UI.
 *
 * PUNTOS CLAVE PARA LA REESCRITURA:
 * 1. Desacoplamiento de estado (State Hoisting): La lógica interna de la cuenta atrás (`CountdownTimer`)
 * no debe calcularse dentro del componente visual. Los cálculos del tiempo (días, horas, etc.)
 * y la corrutina (`LaunchedEffect`) deben extraerse al `ViewModel`, y la vista solo debe
 * limitarse a pintar los datos ya formateados.
 * 2. Adaptabilidad (Responsiveness): El layout abusa de dimensiones fijas y hardcodeadas
 * (`height(210.dp)`, `width(130.dp)`). Esto es frágil y puede romper la interfaz en teléfonos
 * con pantallas pequeñas o si el usuario tiene la fuente del sistema agrandada (accesibilidad).
 * Se deben usar modificadores flexibles como `weight()` o `IntrinsicSize`.
 * 3. Gestión de imágenes: El componente asume recursos locales (`painterResource(id = drop.imageRes)`).
 * Debe migrarse para utilizar `AsyncImage` (Coil) y soportar URLs de red de manera dinámica.
 *
 * @param drop Estructura de datos con la información del producto.
 * @param onReserveClick Función a ejecutar al accionar el botón de reserva.
 * @param onBellClick Función a ejecutar al interactuar con el icono de notificaciones.
 */
@Composable
fun DropCard(
    drop: Drop,
    onReserveClick: () -> Unit,
    onBellClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(130.dp)
                    .background(Color(0xFFF7F7F7)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = drop.imageRes),
                    contentDescription = drop.title,
                    modifier = Modifier.fillMaxHeight().padding(vertical = 16.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = drop.status,
                        color = drop.statusColor,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    Surface(
                        shape = CircleShape,
                        shadowElevation = 2.dp,
                        color = Color.White,
                        modifier = Modifier.size(32.dp)
                    ) {
                        IconButton(onClick = onBellClick) {
                            Icon(
                                imageVector = if (drop.isNotified) Icons.Filled.Notifications else Icons.Outlined.Notifications,
                                contentDescription = "Notificar",
                                tint = if (drop.isNotified) Color.Black else Color.Gray,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }

                Column {
                    Text(text = drop.title, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = Color.Black)
                    Text(text = drop.collection, fontSize = 13.sp, color = Color.DarkGray)
                }
                CountdownTimer(targetTimestamp = drop.targetTimestamp)
                OutlinedButton(
                    onClick = onReserveClick,
                    modifier = Modifier.fillMaxWidth().height(36.dp),
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(1.dp, Color(0xFFEBEBEB))
                ) {
                    Text("Reservar", color = Color(0xFF007AFF), fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
            }
        }
    }
}

@Composable
fun CountdownTimer(targetTimestamp: Long) {
    // Estado local para calcular cuánto tiempo falta
    var timeLeft by remember { mutableLongStateOf(targetTimestamp - System.currentTimeMillis()) }

    LaunchedEffect(targetTimestamp) {
        while (timeLeft > 0) {
            delay(1000L) // Espera un segundo exacto
            timeLeft = targetTimestamp - System.currentTimeMillis() // Recalcula el tiempo restante
        }
    }
    val days = maxOf(0L, TimeUnit.MILLISECONDS.toDays(timeLeft))
    val hours = maxOf(0L, TimeUnit.MILLISECONDS.toHours(timeLeft) % 24)
    val minutes = maxOf(0L, TimeUnit.MILLISECONDS.toMinutes(timeLeft) % 60)
    val seconds = maxOf(0L, TimeUnit.MILLISECONDS.toSeconds(timeLeft) % 60)

    val dStr = String.format("%02d", days)
    val hStr = String.format("%02d", hours)
    val mStr = String.format("%02d", minutes)
    val sStr = String.format("%02d", seconds)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TimeUnitText(value = dStr, label = "DÍAS")
        TimerSeparator()
        TimeUnitText(value = hStr, label = "HRS")
        TimerSeparator()
        TimeUnitText(value = mStr, label = "MIN")
        TimerSeparator()
        TimeUnitText(value = sStr, label = "SEG")
    }
}

@Composable
fun TimeUnitText(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = Color.Black)
        Text(text = label, fontSize = 8.sp, color = Color.Gray, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun TimerSeparator() {
    Text(
        text = ":",
        fontWeight = FontWeight.ExtraBold,
        fontSize = 18.sp,
        color = Color.Black,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}