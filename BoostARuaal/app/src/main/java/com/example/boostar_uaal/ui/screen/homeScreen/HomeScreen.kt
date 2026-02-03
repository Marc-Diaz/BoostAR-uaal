package com.example.boostar_uaal.ui.screen.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.R

import com.example.boostar_uaal.core.components.InterText
import com.example.boostar_uaal.core.components.ItemCarrousel
import com.example.boostar_uaal.core.theme.BoostARuaalTheme
import com.example.boostar_uaal.core.theme.authPrimaryButtonColor
import com.example.boostar_uaal.navigation.Routes


@Composable
fun HomeScreen(navigateTo: (Routes) -> Unit){
    Column(Modifier.fillMaxSize()) {
        Row(Modifier.fillMaxWidth()) {
            InterText(
                text = "Home",
                fontSize = 38.sp
            )
        }
        Row(Modifier.fillMaxWidth()) {
            Card(
                shape = CircleShape,
                modifier = Modifier.width(90.dp)
            ) {
                InterText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    text = "Para ti",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = authPrimaryButtonColor

                )
            }
        }
        Card() {
            Image(
                painter = painterResource(R.drawable.home),
                contentDescription = "Home Hero"
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
        HomeScreen({})
}