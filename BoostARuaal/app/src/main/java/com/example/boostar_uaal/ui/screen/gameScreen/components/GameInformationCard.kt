package com.example.boostar_uaal.ui.screen.gameScreen.components

import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.components.InterText
import com.example.boostar_uaal.core.entities.UserGameStats
import com.example.boostar_uaal.core.theme.primaryColor

@Composable
fun GameUserInformationCard(userStats: UserGameStats){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = primaryColor,
                    shape = RoundedCornerShape(50.dp)
                ) {
                    InterText(
                        text = "Nivel ${userStats.level}",
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }

                Surface(
                    color = Color(0xFFEFEFEF),
                    shape = RoundedCornerShape(50.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            tint = primaryColor,
                            painter = painterResource(R.drawable.boostar_logo),
                            contentDescription = "Boostar points"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        InterText(
                            text = "${userStats.points} pts",
                            color = primaryColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))


            Surface(
                color = primaryColor.copy(alpha = 0.1f),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, primaryColor.copy(alpha = 0.5f))
            ) {
                InterText(
                    text = userStats.title,
                    color = primaryColor,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    fontWeight = FontWeight.Black,
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Fila de Info de XP y Siguiente Nivel
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                InterText(
                    text = "${userStats.currentXp} / ${userStats.totalXp} XP",
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                InterText(
                    text = "Nivel ${userStats.level}",
                    color = primaryColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            LinearProgressIndicator(
                progress = { userStats.currentXp.toFloat() / userStats.totalXp.toFloat() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp),
                color = Color.Green,
                trackColor = Color.LightGray,
                strokeCap = StrokeCap.Round,
                gapSize = (-15).dp,
                drawStopIndicator = { }
            )
        }
    }
}

@Preview
@Composable
fun PreviewGameUserInformationCard(){
    val userStats = UserGameStats(
        level = 8,
        currentXp = 3400,
        totalXp = 5000,
        points = 1240,
        title = "PRINCIPIANTE"
    )
    GameUserInformationCard(userStats)
}