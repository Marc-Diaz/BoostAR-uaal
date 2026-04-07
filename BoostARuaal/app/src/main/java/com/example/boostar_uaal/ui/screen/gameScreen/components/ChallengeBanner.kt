package com.example.boostar_uaal.ui.screen.gameScreen.components
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.components.InterText
import com.example.boostar_uaal.core.theme.primaryButtonColor


@Composable
fun ChallengeBanner() {
    val gradientColors = listOf(
        Color(0xFF8E44AD),
        Color(0xFFE91E63),
        Color(0xFFFF8A80)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(colors = gradientColors),
                    shape = RoundedCornerShape(32.dp)
                )
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = Color.White,
                modifier = Modifier.align(Alignment.Start)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    InterText("🔥", fontSize = 16.sp)
                    Spacer(Modifier.width(8.dp))
                    InterText(
                        text = "DESAFÍO ACTIVO",
                        color = Color(0xFF007BFF),
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            InterText(
                text = "Domina la\ncolorimetría",
                color = Color.White,
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                lineHeight = 44.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            InterText(
                text = "Aprende a combinar colores y crea un outfit con AR que refleje tu paleta personal.",
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 15.sp,
                lineHeight = 20.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Iconos de estadísticas
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StatItem(R.drawable.boostar_logo, Color.White, "~15 min")
                Spacer(Modifier.width(16.dp))
                StatItem(R.drawable.boostar_logo, Color.White, "+250 XP")
                Spacer(Modifier.width(16.dp))
                StatItem(R.drawable.boostar_logo, primaryButtonColor, "+180 pts")
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Botón "Empezar" con sombra
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp)
                    .shadow(12.dp, RoundedCornerShape(32.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF)),
                shape = RoundedCornerShape(32.dp)
            ) {
                InterText(
                    text = "Empezar",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun StatItem(icon: Int, color: Color, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(16.dp)
        )
        Spacer(Modifier.width(4.dp))
        InterText(text, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewChallengeCard() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ChallengeBanner()
    }
}